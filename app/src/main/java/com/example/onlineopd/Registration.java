package com.example.onlineopd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Registration extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    Button button;
     ImageView imageView ;
    Spinner aSpin;
    Uri filePath;
    EditText name2;
    EditText password;
    EditText email2 ;
    Button nextBtn;
    Button nextBtn2;
    String UserID;
    FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    Uri imagesuri ;
    // Spinner genders;
  //  Spinner bloodhell;
    Spinner bSpin;
// private  static final int    IMAGE_PICK_CODE = 1000;
//    private  static final int     PERMISSION_CODE = 1001;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference(); 
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
         button = findViewById(R.id.choseImage);
        password = findViewById(R.id.password);
        email2 = findViewById(R.id.email);
        name2  = findViewById(R.id.name2);
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn2 = findViewById(R.id.nextBtn2);
        imageView = findViewById(R.id.profileImage);

        aSpin = findViewById(R.id.gender);

        nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, signUpp.class);
                startActivity(intent);
            }
        });

      bSpin  = findViewById(R.id.blood);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.Spinner_array,
                android.R.layout.simple_spinner_item

        );

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Registration.this, HomePage.class);
//                                intent.putExtra("HEREITIS",name2.getText().toString());
//                                startActivity(intent);
//                                finish();
                final ProgressDialog pd = new ProgressDialog(Registration.this);
                pd.setMessage("Processing....");
                pd.show();

                String myEmail  = email2.getText().toString();
                String myPassword = password.getText().toString();
                if(name2.getText().toString().isEmpty())
                {
                    name2.setError("Please enter the name" );
                    name2.requestFocus();
                    pd.cancel();
                }
                if(myEmail.isEmpty())
                {
                    email2.setError("Please enter the email  id" );
                    email2.requestFocus();
                    pd.cancel();
                }
                if(myPassword.isEmpty())
                {
                    password.setError("Please enter the password" );
                    password.requestFocus();
                    pd.cancel();
                }
          else      if(!(myEmail.isEmpty() && myPassword.isEmpty()))
                {
                    firebaseAuth.createUserWithEmailAndPassword(myEmail,myPassword).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(Registration.this, "User Already Present / Wrong format of Email",Toast.LENGTH_SHORT).show();
                                pd.cancel();
                            }
                            else
                            {

                                UserID = firebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = firebaseFirestore.collection("Users").document(UserID);
                                Map<String,Object> user  = new HashMap<>();
                                user.put("name",name2.getText().toString());
                                user.put("gender", aSpin.getSelectedItem().toString());
                                user.put("Bloodgroup", bSpin.getSelectedItem().toString());
                                user.put("Email", email2.getText().toString());

                                uploadimagetofirebase2(imagesuri);

                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Registration.this, "Data is stored", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                Intent intent = new Intent(Registration.this, HomePage.class);
                                intent.putExtra("HEREITIS",name2.getText().toString());
                                startActivity(intent);
                            //    finish();
                            }

                        }
                    });

                }
                else
                {
                    pd.cancel();
                    Toast.makeText(Registration.this,  "Error Occured ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aSpin.setAdapter(adapter);
        aSpin.setOnItemSelectedListener(this);
     //   Spinner bSpin = findViewById(R.id.blood);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.bloodSpinner,
                android.R.layout.simple_spinner_item

        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bSpin.setAdapter(adapter1);
        bSpin.setOnItemSelectedListener(this);
//        aSpin.setOnItemClickListener(this);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        bSpin.setAdapter(adapter);
//        bSpin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseImage();
            }
        });

    }

    private void uploadimagetofirebase(Uri data) {
        imagesuri =data;
    }
private void uploadimagetofirebase2(Uri data)
{
    StorageReference fileRef = storageReference.child("users/" + Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid() + "/profile.jpg");
        fileRef.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registration.this, "FAILED", Toast.LENGTH_SHORT).show();
            }
        });

}

    private void choseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK
                && data!=null && data.getData()!=null){
            uploadimagetofirebase(data.getData());
            filePath = data.getData();
            Bitmap  bitmap= null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}