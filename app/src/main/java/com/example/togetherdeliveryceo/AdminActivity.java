package com.example.togetherdeliveryceo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    //final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String TAG = "Hello!!!!!!!!!!!!!!!!!";
    private ListView listView;
    List storeNameList = new ArrayList<>();
    ArrayAdapter adapter;
    static boolean calledAlready = false;

    /*ListView ceoListView;
    public ArrayList ceoArray = new ArrayList();
    myAdapter adapter;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        if(!calledAlready){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true); // 다른 인스턴스보다 먼저 실행되어야 한다.
            calledAlready = true;
        }

        listView = (ListView) findViewById(R.id.ceoList);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_listview_item, storeNameList);
        listView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("ceoInfo");


        databaseRef.child("chu1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // 클래스 모델이 필요?
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    //MyFiles filename = (MyFiles) fileSnapshot.getValue(MyFiles.class);
                    //하위키들의 value를 어떻게 가져오느냐???
                    String str = fileSnapshot.child("storeName").getValue(String.class);
                    Log.i("TAG: value is ", str);
                    storeNameList.add(str);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", "Failed to read value", databaseError.toException());
            }
        });
        System.out.println("1111111111111");
        System.out.println(storeNameList);


    }




    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        db.collection("ceoInfo")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                        }

                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });

        ceoListView = (ListView)findViewById(R.id.ceoList);
        adapter = new myAdapter();
        ceoListView.setAdapter(adapter);
    }*/
    /*class myAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            System.out.println("444444444444444");
            System.out.println(ceoArray.size());
            System.out.println("55555555555555");
            return ceoArray.size();
        }

        @Override
        public Object getItem(int position) {
            return ceoArray.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Button button = new Button(getApplicationContext());
            button.setText((ceoArray.get(position)).toString());
            button.setTextSize(50.0f);
            button.setTextColor(Color.BLUE);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainIntent = new Intent(AdminActivity.this, Main0Activity.class);
                    mainIntent.putExtra("fru", ceoArray.get(position).toString());
                    startActivity(mainIntent);
                }
            });

            return button;
        }
    }*/
}
