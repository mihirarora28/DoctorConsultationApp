package com.example.onlineopd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dr_joshi extends AppCompatActivity {
    Button button;
    Button button2;
    TextView test;
    TextView test2;
    TextView test3;
    CircleImageView circleImageView;
    Toolbar toolbar;
    FirebaseAuth firebaseAuth;
    ArrayList<String> abc;
    FirebaseFirestore firebaseFirestore;
    ArrayList<String> abcd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_joshi);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();




        circleImageView = findViewById(R.id.imaGees);
     abc = new ArrayList<>();




        String UserID;
        UserID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        final DocumentReference documentReference = firebaseFirestore.collection("Users").document(UserID);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {

                    abcd = new ArrayList<>();
                    if(documentSnapshot.get("History") != null)
                    {
                        abcd = (ArrayList<String>) documentSnapshot.get("History");
                        if(abcd.size()!=0)
                            abc = abcd;
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Dr_joshi.this, "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });





















        toolbar = findViewById(R.id.tooldoctor);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        test = findViewById(R.id.test);
        test2 = findViewById(R.id.test2);
        test3 = findViewById(R.id.namedoctor);
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        byte[] byteArray = extras.getByteArray("picture");

        assert byteArray != null;
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


        circleImageView.setImageBitmap(bmp);
        String c = getIntent().getStringExtra("phonenumber");
        String d = getIntent().getStringExtra("email");
        String e = getIntent().getStringExtra("name");

        test.setText(c);
        test2.setText(d);
        test3.setText(e);

        toolbar.setTitle(e);
//
//
//



        final String a = "tel:" + test.getText().toString();
        final String b = "mailto:" + test2.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

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

                            Map<String, Object> user  = new HashMap<>();
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy,hh:mm:ss a,");
                            String dateTime = simpleDateFormat.format(calendar.getTime());

                            String d = dateTime + "CALLED " + test3.getText().toString() + ",";
                            abc.add(d);
                            user.put("History",abc);
                            user.put("name",name);
                            user.put("Email",email);
                            user.put("gender",gender);
                            user.put("Bloodgroup",bloodGroup);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Dr_joshi.this, "SAVED TO ACTIVITY", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Dr_joshi.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });

                if (ActivityCompat.checkSelfPermission(Dr_joshi.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(a));
                    startActivity(intent);
                }
            }
        });












        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                            Map<String, Object> user  = new HashMap<>();
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy,hh:mm:ss a,");
                            String dateTime = simpleDateFormat.format(calendar.getTime());

                            String d = dateTime + "EMAILED " + test3.getText().toString() + ",";;
                            abc.add(d);
                            user.put("History",abc);
                            user.put("name",name);
                            user.put("Email",email);
                            user.put("gender",gender);
                            user.put("Bloodgroup",bloodGroup);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Dr_joshi.this, "SAVED TO ACTIVITY", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Dr_joshi.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(b));

                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
    }
    //}
    }
