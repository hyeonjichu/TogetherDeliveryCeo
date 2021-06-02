package com.example.togetherdeliveryceo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String ceoId;
    private String TAG = "Hello!!!!!!!!!!!!!!!!!";
    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView myStore;
    Button updateBtn, menuAddBtn;

    RecyclerView mMenuListView;
    ArrayList<OrderModel> orderModelArrayList;
    OrderAdapter orderAdapter;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myStore = (TextView)findViewById(R.id.myStore);
        updateBtn = (Button)findViewById(R.id.updateBtn);
        menuAddBtn = (Button)findViewById(R.id.menuAddBtn);

        Intent MainIntent = getIntent();
        ceoId = MainIntent.getStringExtra("id");

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        mMenuListView = findViewById(R.id.orderList);
        mMenuListView.setHasFixedSize(true);
        mMenuListView.setLayoutManager(new LinearLayoutManager(this));

        firebaseFirestore = FirebaseFirestore.getInstance();
        orderModelArrayList = new ArrayList<>();

        orderAdapter = new OrderAdapter(MainActivity.this, orderModelArrayList);
        orderAdapter.setOnItemClickListener((v, position) -> {
            Intent intent = new Intent(MainActivity.this, MenuListActivity.class);
            intent.putExtra("orderId", orderModelArrayList.get(position).orderId);
            intent.putExtra("store", orderModelArrayList.get(position).store);
            intent.putExtra("price", orderModelArrayList.get(position).price);
            intent.putExtra("ranNum", orderModelArrayList.get(position).ranNum);
            intent.putExtra("ceoId", ceoId);
            startActivity(intent);
        });
        mMenuListView.setAdapter(orderAdapter);
        EventChangeListener();

        DocumentReference doc = firebaseFirestore.collection("ceoInfo").document(ceoId);
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        myStore.setText(document.get("storeName").toString());
                        myStore.setTextSize(40);
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(MainActivity.this, UpdateActivity.class);
                updateIntent.putExtra("ceoId",ceoId);
                startActivity(updateIntent);
            }
        });

        menuAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuAddIntent = new Intent(MainActivity.this, MenuAddActivity.class);
                menuAddIntent.putExtra("ceoId",ceoId);
                startActivity(menuAddIntent);
            }
        });
    }
    private void EventChangeListener() {
        firebaseFirestore.collection("shopBag")
                .whereEqualTo("store",ceoId)
                .whereNotEqualTo("approval","no")
                .whereEqualTo("complete","no")
                .addSnapshotListener((value, error) -> {
                    if(error != null){
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Firestore error", error.getMessage());
                        return;
                    }
                    for (DocumentChange dc : value.getDocumentChanges()){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            orderModelArrayList.add(dc.getDocument().toObject(OrderModel.class));
                        }
                        orderAdapter.notifyDataSetChanged();
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                });
    }
}