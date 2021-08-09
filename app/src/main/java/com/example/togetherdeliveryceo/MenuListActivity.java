package com.example.togetherdeliveryceo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuListActivity extends AppCompatActivity {

    String orderId, storeId, price, ranNum, ceoId, state, place;
    TextView orderName, placeText, text_price;
    RecyclerView order_list_recycler;
    MyMenuAdapter myMenuAdapter;
    ArrayList<MenuModel> menuModelsArrayList;
    ArrayList<Object> objects;
    MenuModel menuModel,menuModel2,menuModel3,menuModel4,menuModel5,menuModel6,menuModel7,menuModel8, menuModel9,menuModel10;
    Button acceptBtn, rejectBtn, completeBtn;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    private String TAG = "Hello!!!!!!!!!!!!!!!!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menulist);
        getSupportActionBar().setIcon(R.drawable.delivery);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent MainIntent = getIntent();
        orderId = MainIntent.getStringExtra("orderId");
        storeId = MainIntent.getStringExtra("storeId");
        price = MainIntent.getStringExtra("price");
        ranNum = MainIntent.getStringExtra("ranNum");
        ceoId = MainIntent.getStringExtra("ceoId");

        text_price = findViewById(R.id.text_price);
        text_price.setText(price);

        orderName = findViewById(R.id.orderName);
        orderName.setText(orderId + "님의 주문입니다.");

        placeText = findViewById(R.id.order_list_place);


        acceptBtn = findViewById(R.id.acceptBtn);
        rejectBtn = findViewById(R.id.rejectBtn);
        completeBtn = findViewById(R.id.completeBtn);

        acceptBtn.setVisibility(View.GONE);
        acceptBtn.setEnabled(false);
        rejectBtn.setVisibility(View.GONE);
        rejectBtn.setEnabled(false);
        completeBtn.setVisibility(View.GONE);
        completeBtn.setEnabled(false);



        menuModelsArrayList = new ArrayList<MenuModel>();
        menuModel = new MenuModel();

        order_list_recycler = findViewById(R.id.order_list_recycler);
        order_list_recycler.setHasFixedSize(true);
        order_list_recycler.setLayoutManager(new LinearLayoutManager(this));
        myMenuAdapter = new MyMenuAdapter(MenuListActivity.this,menuModelsArrayList);
        order_list_recycler.setAdapter(myMenuAdapter);




        db = FirebaseFirestore.getInstance();

        db.collection("shopBag").document(ranNum).collection(ranNum)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                if(document.getData().get("menu1") != null) {
                                    menuModel = new MenuModel();
                                    HashMap hashMap = (HashMap) document.getData().get("menu1");
                                    menuModel.menuName = (String) hashMap.get("menuName");
                                    menuModel.menuPrice = (String) hashMap.get("menuPrice");
                                    menuModelsArrayList.add(menuModel);
                                }
                                if(document.getData().get("menu2") != null) {
                                    menuModel2 = new MenuModel();
                                    HashMap hashMap2 = (HashMap) document.getData().get("menu2");
                                    menuModel2.menuName = (String) hashMap2.get("menuName");
                                    menuModel2.menuPrice = (String) hashMap2.get("menuPrice");
                                    menuModelsArrayList.add(menuModel2);
                                }
                                if(document.getData().get("menu3") != null) {
                                    menuModel3 = new MenuModel();
                                    HashMap hashMap3 = (HashMap) document.getData().get("menu3");
                                    menuModel3.menuName = (String) hashMap3.get("menuName");
                                    menuModel3.menuPrice = (String) hashMap3.get("menuPrice");
                                    menuModelsArrayList.add(menuModel3);
                                }
                                if(document.getData().get("menu4") != null) {
                                    menuModel4 = new MenuModel();
                                    HashMap hashMap4 = (HashMap) document.getData().get("menu4");
                                    menuModel4.menuName = (String) hashMap4.get("menuName");
                                    menuModel4.menuPrice = (String) hashMap4.get("menuPrice");
                                    menuModelsArrayList.add(menuModel4);
                                }
                                if(document.getData().get("menu5") != null) {
                                    menuModel5 = new MenuModel();
                                    HashMap hashMap5 = (HashMap) document.getData().get("menu5");
                                    menuModel5.menuName = (String) hashMap5.get("menuName");
                                    menuModel5.menuPrice = (String) hashMap5.get("menuPrice");
                                    menuModelsArrayList.add(menuModel5);
                                }
                                if(document.getData().get("menu6") != null) {
                                    menuModel6 = new MenuModel();
                                    HashMap hashMap6 = (HashMap) document.getData().get("menu6");
                                    menuModel6.menuName = (String) hashMap6.get("menuName");
                                    menuModel6.menuPrice = (String) hashMap6.get("menuPrice");
                                    menuModelsArrayList.add(menuModel6);
                                }
                                if(document.getData().get("menu7") != null) {
                                    menuModel7 = new MenuModel();
                                    HashMap hashMap7 = (HashMap) document.getData().get("menu7");
                                    menuModel7.menuName = (String) hashMap7.get("menuName");
                                    menuModel7.menuPrice = (String) hashMap7.get("menuPrice");
                                    menuModelsArrayList.add(menuModel7);
                                }
                                if(document.getData().get("menu8") != null) {
                                    menuModel8 = new MenuModel();
                                    HashMap hashMap8 = (HashMap) document.getData().get("menu8");
                                    menuModel8.menuName = (String) hashMap8.get("menuName");
                                    menuModel8.menuPrice = (String) hashMap8.get("menuPrice");
                                    menuModelsArrayList.add(menuModel8);
                                }
                                if(document.getData().get("menu9") != null) {
                                    menuModel9 = new MenuModel();
                                    HashMap hashMap9 = (HashMap) document.getData().get("menu9");
                                    menuModel9.menuName = (String) hashMap9.get("menuName");
                                    menuModel9.menuPrice = (String) hashMap9.get("menuPrice");
                                    menuModelsArrayList.add(menuModel9);
                                }
                                if(document.getData().get("menu10") != null) {
                                    menuModel10 = new MenuModel();
                                    HashMap hashMap10 = (HashMap) document.getData().get("menu10");
                                    menuModel10.menuName = (String) hashMap10.get("menuName");
                                    menuModel10.menuPrice = (String) hashMap10.get("menuPrice");
                                    menuModelsArrayList.add(menuModel10);
                                }
                                myMenuAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

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
                                place = document.getData().get("place").toString();

                                placeText.setText(place);
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
                                            db.collection("shopBag").document(ranNum)
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
                                            db.collection("shopBag").document(ranNum)
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
                                            db.collection("shopBag").document(ranNum)
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
                           }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }



}
