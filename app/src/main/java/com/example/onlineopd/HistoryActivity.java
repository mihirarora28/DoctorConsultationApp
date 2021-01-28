package com.example.onlineopd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HistoryActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth ;
    FirebaseFirestore firebaseFirestore;
    TextView textView;
    ArrayList<String> abc;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        firebaseAuth  = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

recyclerView = findViewById(R.id.ry11);


        String UserID;
        UserID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        final DocumentReference documentReference = firebaseFirestore.collection("Users").document(UserID);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    String name = documentSnapshot.getString("name");
                    String gender = documentSnapshot.getString("gender");
                    String email = documentSnapshot.getString("Email");
                    String bloodGroup = documentSnapshot.getString("Bloodgroup");
                abc = new ArrayList<>();
                if(documentSnapshot.get("History") == null)
                {
                    Intent intent = new Intent(HistoryActivity.this, noactivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
                           abc = (ArrayList<String>) documentSnapshot.get("History");
                    assert abc != null;
//                    String a = abc.get(0);

                    historyclass ad = new historyclass(HistoryActivity.this,abc);
                    recyclerView.setAdapter(ad);
                    recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
                    //TODO

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(HistoryActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

}