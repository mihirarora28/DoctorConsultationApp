package com.example.onlineopd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class editProfile2 extends AppCompatActivity {
EditText name ;
EditText blood;
TextView email ;
EditText gender;
 ImageView image;
Button save;
FirebaseUser user;
    String UserID;

    StorageReference storageReference;
    FirebaseAuth firebaseAuth ;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile2);





       String a =  getIntent().getStringExtra("Namesss");
       String b =  getIntent().getStringExtra("BLOODsss");
       String c =  getIntent().getStringExtra("GENDERsss");
       String d = getIntent().getStringExtra("EMAILsss");


       save = findViewById(R.id.save);
        firebaseAuth  = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
image = findViewById(R.id.profileImage3);
       name = findViewById(R.id.name2);
       blood = findViewById(R.id.bloodgroup);
       email = findViewById(R.id.email4);
       gender = findViewById(R.id.genderplz);
       name.setText(a);
       blood.setText(b);
       email.setText(d);
       gender.setText(c);

        final ProgressDialog pd = new ProgressDialog(editProfile2.this);
        pd.setMessage("Fetching Your Information");
        pd.show();
        StorageReference profile = storageReference. child("users/" + Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid() + "/profile.jpg");
        profile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(image);
                pd.cancel();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty())
                {
                    name.setError("Please enter the name" );
                    name.requestFocus();
                }
             else   if(blood.getText().toString().isEmpty())
                {
                    blood.setError("Please enter the email  id" );
                    blood.requestFocus();
                }
              else  if(gender.getText().toString().isEmpty())
                {
                    gender.setError("Please enter the password" );
                    gender.requestFocus();

                }
               else
                {
//                    user.updateEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
                    final ProgressDialog pd = new ProgressDialog(editProfile2.this);
                    pd.setMessage("Saving Data...");
                    pd.show();
                            UserID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("Users").document(UserID);
                            Map<String,Object> map = new HashMap<>();
                            map.put("name",name.getText().toString());
                            map.put("gender",gender.getText().toString());
                            map.put("Bloodgroup",blood.getText().toString());
                            documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    pd.cancel();
                                    Toast.makeText(editProfile2.this, "Information is Updated", Toast.LENGTH_SHORT).show();

                                }
                            });
                }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(editProfile2.this, "Error Occured", Toast.LENGTH_SHORT).show();
//                        }
//                    });


            }
        });
    }
}