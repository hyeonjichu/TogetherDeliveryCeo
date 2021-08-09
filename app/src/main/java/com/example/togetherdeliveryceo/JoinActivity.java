package com.example.togetherdeliveryceo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class JoinActivity extends AppCompatActivity {

    EditText id, pw, pwChk, name, phone, email, storeName, storeAddress, storeNum, businessNum;
    private String TAG = "Hello";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        getSupportActionBar().setIcon(R.drawable.delivery);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        id=(EditText)findViewById(R.id.id);
        pw=(EditText)findViewById(R.id.pw);
        pwChk=(EditText)findViewById(R.id.pwChk);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
        storeName=(EditText)findViewById(R.id.storeName);
        storeAddress=(EditText)findViewById(R.id.storeAddr);
        storeNum=(EditText)findViewById(R.id.storeNum);
        businessNum=(EditText)findViewById(R.id.businessNum);
    }
    public void onClickJoinOk(View view){
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final String j_id=id.getText().toString();
        final String j_pw=pw.getText().toString();
        final String j_name=name.getText().toString();
        final String j_phone=phone.getText().toString();
        final String j_email=email.getText().toString();
        final String j_storeName=storeName.getText().toString();
        final String j_storeAddress=storeAddress.getText().toString();
        final String j_storeNum=storeNum.getText().toString();
        final String j_businessNum=businessNum.getText().toString();

        Map<String, Object> newUser = new HashMap<>();
        newUser.put("id",j_id);
        newUser.put("pw",j_pw);
        newUser.put("name",j_name);
        newUser.put("phone",j_phone);
        newUser.put("email",j_email);
        newUser.put("storeName",j_storeName);
        newUser.put("storeAddr",j_storeAddress);
        newUser.put("storeNum",j_storeNum);
        newUser.put("businessNum",j_businessNum);
        newUser.put("authYn",0);

        db.collection("ceoInfo").document(j_id)
                .set(newUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        Intent loginIntent = new Intent(JoinActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });


    }
    public void onClickBack(View view){
        Intent loginIntent = new Intent(JoinActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}
