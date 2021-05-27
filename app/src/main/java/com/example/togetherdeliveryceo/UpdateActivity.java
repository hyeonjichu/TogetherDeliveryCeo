package com.example.togetherdeliveryceo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    String ceoId;
    EditText storeMainInfo;
    Button updateOkBtn;
    private String TAG = "Hello!!!!!!!!!!!!!!!!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent MenuAddIntent = getIntent();
        ceoId = MenuAddIntent.getStringExtra("ceoId");

        storeMainInfo = (EditText)findViewById(R.id.storeMainInfo);
        updateOkBtn = (Button)findViewById(R.id.updateOkBtn);

        DocumentReference doc = db.collection("ceoInfo").document(ceoId);
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        storeMainInfo.setText(document.get("storeInfo").toString());
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        updateOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String storeInfo = storeMainInfo.getText().toString();
                Map<String, Object> store = new HashMap<>();
                store.put("storeInfo", storeInfo);

                db.collection("ceoInfo").document(ceoId)
                    .set(store, SetOptions.merge())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                            Intent MainIntent = new Intent(UpdateActivity.this, MainActivity.class);
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
