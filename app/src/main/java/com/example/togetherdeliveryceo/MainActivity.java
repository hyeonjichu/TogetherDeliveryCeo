package com.example.togetherdeliveryceo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    String ceoId;
    private String TAG = "Hello!!!!!!!!!!!!!!!!!";
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView myStore;
    Button updateBtn, menuAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myStore = (TextView)findViewById(R.id.myStore);
        updateBtn = (Button)findViewById(R.id.updateBtn);
        menuAddBtn = (Button)findViewById(R.id.menuAddBtn);

        Intent MainIntent = getIntent();
        ceoId = MainIntent.getStringExtra("id");

        DocumentReference doc = db.collection("ceoInfo").document(ceoId);
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
}