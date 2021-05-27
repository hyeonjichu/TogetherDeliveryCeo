package com.example.togetherdeliveryceo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MenuAddActivity extends AppCompatActivity {

    String ceoId;
    EditText menuName, menuPrice, menuInfo;
    Button menuOkBtn;
    private String TAG = "Hello!!!!!!!!!!!!!!!!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuadd);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent MenuAddIntent = getIntent();
        ceoId = MenuAddIntent.getStringExtra("ceoId");

        menuName = (EditText)findViewById(R.id.menuName);
        menuPrice = (EditText)findViewById(R.id.menuPrice);
        menuInfo = (EditText)findViewById(R.id.menuInfo);
        menuOkBtn = (Button)findViewById(R.id.menuOkBtn);

        menuOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String addName = menuName.getText().toString();
                final String addPrice = menuPrice.getText().toString();
                final String addInfo = menuInfo.getText().toString();

                System.out.println("222222222");
                System.out.println(addName);
                System.out.println(addInfo);
                System.out.println("333333333333");


                Map<String, Object> newMenu = new HashMap<>();
                newMenu.put("ceoId",ceoId);
                newMenu.put("menuName",addName);
                newMenu.put("menuPrice", addPrice);
                newMenu.put("menuInfo",addInfo);

                db.collection("storeMenu")
                    .add(newMenu)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                            Intent MainIntent = new Intent(MenuAddActivity.this, MainActivity.class);
                            MainIntent.putExtra("id",ceoId);
                            startActivity(MainIntent);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });


            }
        });
    }
}
