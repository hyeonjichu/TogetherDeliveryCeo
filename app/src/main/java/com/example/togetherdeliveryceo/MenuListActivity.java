package com.example.togetherdeliveryceo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class MenuListActivity extends AppCompatActivity {

    String orderId, store, price, ranNum, ceoId, state;
    TextView orderName,menuList;
    Button acceptBtn, rejectBtn, completeBtn;
    FirebaseFirestore db;
    private String TAG = "Hello!!!!!!!!!!!!!!!!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menulist);

        Intent MainIntent = getIntent();
        orderId = MainIntent.getStringExtra("orderId");
        store = MainIntent.getStringExtra("store");
        price = MainIntent.getStringExtra("price");
        ranNum = MainIntent.getStringExtra("ranNum");
        ceoId = MainIntent.getStringExtra("ceoId");

        orderName = findViewById(R.id.orderName);
        orderName.setText(orderId + "님의 주문입니다.");

        menuList = findViewById(R.id.menuList);
        acceptBtn = findViewById(R.id.acceptBtn);
        rejectBtn = findViewById(R.id.rejectBtn);
        completeBtn = findViewById(R.id.completeBtn);

        acceptBtn.setVisibility(View.GONE);
        acceptBtn.setEnabled(false);
        rejectBtn.setVisibility(View.GONE);
        rejectBtn.setEnabled(false);
        completeBtn.setVisibility(View.GONE);
        completeBtn.setEnabled(false);

        db = FirebaseFirestore.getInstance();

        db.collection("shopBag")
                .whereEqualTo("ranNum", ranNum)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                state = document.getData().get("approval").toString();
                                if(state.equals("waiting")){
                                    acceptBtn.setVisibility(View.VISIBLE);
                                    acceptBtn.setEnabled(true);
                                    rejectBtn.setVisibility(View.VISIBLE);
                                    rejectBtn.setEnabled(true);
                                    completeBtn.setVisibility(View.GONE);
                                    completeBtn.setEnabled(false);
                                    acceptBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Map<String, Object> approval = new HashMap<>();
                                            approval.put("approval", "yes");
                                            db.collection("shopBag").document("커피")
                                                    .set(approval, SetOptions.merge())
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d(TAG, "DocumentSnapshot successfully written!");
                                                            Intent MainIntent = new Intent(MenuListActivity.this, MainActivity.class);
                                                            MainIntent.putExtra("id",ceoId);
                                                            startActivity(MainIntent);
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w(TAG, "Error writing document", e);
                                                        }
                                                    });

                                        }
                                    });

                                    rejectBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Map<String, Object> approval = new HashMap<>();
                                            approval.put("approval", "no");
                                            db.collection("shopBag").document("커피")
                                                    .set(approval, SetOptions.merge())
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d(TAG, "DocumentSnapshot successfully written!");
                                                            Intent MainIntent = new Intent(MenuListActivity.this, MainActivity.class);
                                                            MainIntent.putExtra("id",ceoId);
                                                            startActivity(MainIntent);
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w(TAG, "Error writing document", e);
                                                        }
                                                    });

                                        }
                                    });
                                }else if(state.equals("yes")){
                                    System.out.println("2222222222222222222222222222");
                                    completeBtn.setVisibility(View.VISIBLE);
                                    completeBtn.setEnabled(true);
                                    acceptBtn.setVisibility(View.GONE);
                                    acceptBtn.setEnabled(false);
                                    rejectBtn.setVisibility(View.GONE);
                                    rejectBtn.setEnabled(false);
                                    completeBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Map<String, Object> complete = new HashMap<>();
                                            complete.put("complete", "yes");
                                            db.collection("shopBag").document("커피")
                                                    .set(complete, SetOptions.merge())
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d(TAG, "DocumentSnapshot successfully written!");
                                                            Intent MainIntent = new Intent(MenuListActivity.this, MainActivity.class);
                                                            MainIntent.putExtra("id",ceoId);
                                                            startActivity(MainIntent);
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w(TAG, "Error writing document", e);
                                                        }
                                                    });
                                        }
                                    });
                                }
                                menuList.append(document.getData().get("menu1").toString());
                                menuList.append("\n");
                                if(document.getData().get("menu2") != null){
                                    menuList.append(document.getData().get("menu2").toString());
                                    menuList.append("\n");
                                }if(document.getData().get("menu3") != null){
                                    menuList.append(document.getData().get("menu3").toString());
                                    menuList.append("\n");
                                }if(document.getData().get("menu4") != null){
                                    menuList.append(document.getData().get("menu4").toString());
                                    menuList.append("\n");
                                }if(document.getData().get("menu5") != null){
                                    menuList.append(document.getData().get("menu5").toString());
                                    menuList.append("\n");
                                }if(document.getData().get("menu6") != null){
                                    menuList.append(document.getData().get("menu6").toString());
                                    menuList.append("\n");
                                }if(document.getData().get("menu7") != null){
                                    menuList.append(document.getData().get("menu7").toString());
                                    menuList.append("\n");
                                }if(document.getData().get("menu8") != null){
                                    menuList.append(document.getData().get("menu8").toString());
                                    menuList.append("\n");
                                }if(document.getData().get("menu9") != null){
                                    menuList.append(document.getData().get("menu9").toString());
                                    menuList.append("\n");
                                }if(document.getData().get("menu10") != null){
                                    menuList.append(document.getData().get("menu10").toString());
                                    menuList.append("\n");
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
