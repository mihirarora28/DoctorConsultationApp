package com.example.onlineopd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Objects;

public class EditProfile extends AppCompatActivity {
    StorageReference storageReference;
    FirebaseAuth firebaseAuth ;
    ImageView imageview;
    Button resetPassword;
    Button editImage;
Button saveImage;
Button editProfile;
    String UserID;
    TextView names;
    TextView emails ;
    TextView bloods;
    TextView genders;

    FirebaseFirestore firebaseFirestore ;



    DocumentReference documentReference;
    Uri imagesuri ;

    Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        imageview = (ImageView)findViewById(R.id.imagesEdit);
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth  = FirebaseAuth.getInstance();
        resetPassword = findViewById(R.id.resetPassword);
        editImage = findViewById(R.id.editImage);
        saveImage = findViewById(R.id.saveImage);
        editProfile = findViewById(R.id.editProfile);
        names = findViewById(R.id.namesss);
        emails = findViewById(R.id.emailsss);
        bloods = findViewById(R.id.blooddd);
        genders = findViewById(R.id.GENDERSSS);

        firebaseFirestore = FirebaseFirestore.getInstance();

        UserID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
         documentReference = firebaseFirestore.collection("Users").document(UserID);


        final ProgressDialog pd = new ProgressDialog(EditProfile.this);
        pd.setMessage("Fetching Your Information");
        pd.show();
        loadData() ;
        StorageReference profile = storageReference. child("users/" + Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid() + "/profile.jpg");
        profile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageview);
                pd.hide();
            }
        });
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        final EditText editText =  new EditText(view.getContext());
                         AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                        alertDialog.setTitle("Reset Password");
                        alertDialog.setMessage("Enter email address to reset the password");
                        alertDialog.setView(editText);
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String mail = editText.getText().toString();
                                firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(EditProfile.this, "Rest link has been sent!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(EditProfile.this, "Try Again", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertDialog.create().show();

                    }
                });


        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choseImage();

            }
        });

        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadimagetofirebase2(imagesuri);
            }
        });


        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, editProfile2.class);
                intent.putExtra("Namesss",names.getText().toString());
                intent.putExtra("EMAILsss", emails.getText().toString());
                intent.putExtra("BLOODsss", bloods.getText().toString());
                intent.putExtra("GENDERsss", genders.getText().toString());
                startActivity(intent);
            }
        });



            }

    private void loadData() {
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {
                    String name  = documentSnapshot.getString("name");
                    String gender  = documentSnapshot.getString("gender");
                    String email  = documentSnapshot.getString("Email");
                    String bloodGroup  = documentSnapshot.getString("Bloodgroup");
                    names.setText(name);
                    genders.setText(gender);
                    emails.setText(email);
                    bloods.setText(bloodGroup);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(EditProfile.this, "Error Occured", Toast.LENGTH_SHORT).show();
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
            saveImage.setEnabled(true);
            uploadimagetofirebase(data.getData());
            filePath = data.getData();
            Bitmap bitmap= null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadimagetofirebase(Uri data) {
        imagesuri =data;
    }
    private void uploadimagetofirebase2(Uri data)
    {
//        ProgressDialog pd = new ProgressDialog(EditProfile.this);
//        pd.setMessage("Processing....");
//        pd.show();
        final ProgressDialog pd = new ProgressDialog(EditProfile.this);
        pd.setMessage("Saving new image...");
        pd.show();

        StorageReference fileRef = storageReference.child("users/" + Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid() + "/profile.jpg");
        fileRef.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.cancel();
                saveImage.setEnabled(false);
                Toast.makeText(EditProfile.this, "IMAGE SAVED", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.cancel();
                Toast.makeText(EditProfile.this, "FAILED", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
