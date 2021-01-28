package com.example.onlineopd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signUpp extends AppCompatActivity {
EditText email2 ;
EditText passorwd2;
 Button signed;
 FirebaseAuth firebaseAuth ;
 Button existing;
private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_upp);

        firebaseAuth = FirebaseAuth.getInstance();

        email2 = findViewById(R.id.email2);
        passorwd2 = findViewById(R.id.passoword2);
        signed  = findViewById(R.id.signed);
        existing = findViewById(R.id.existing);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                if(mFirebaseUser!=null)
                {
                    Toast.makeText(signUpp.this, "Please fill your existing email and password", Toast.LENGTH_SHORT).show();
               ///     Intent i = new Intent (signUpp.this, Homepage2.class);
               //     startActivity(i);
              //      finish();
                }
                else
                {
                    Toast.makeText(signUpp.this, "Please Login ", Toast.LENGTH_SHORT).show();

                }

            }
        };
        existing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUpp.this,Registration.class );
                startActivity(intent);
                finish();
            }
        });
        signed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog pd = new ProgressDialog(signUpp.this);
                pd.setMessage("Processing....");
                pd.show();
                String myEmail  = email2.getText().toString();
                String myPassword = passorwd2.getText().toString();


                if(myEmail.isEmpty())
                {
                    email2.setError("Please enter the email  id" );
                    email2.requestFocus();
                    pd.cancel();
                }
           else     if(myPassword.isEmpty())
                {
                    passorwd2.setError("Please enter the password" );
                    passorwd2.requestFocus();
                    pd.cancel();
                }
           else     if(!(myEmail.isEmpty() && myPassword.isEmpty()))
                {
                    firebaseAuth.signInWithEmailAndPassword(myEmail,myPassword).addOnCompleteListener(signUpp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(signUpp.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                                pd.cancel();

                            }
                            else
                            {
                                Toast.makeText(signUpp.this,  "WELCOME BACK!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(signUpp.this, Homepage2.class);
                                startActivity(i);
                            }
                        }
                    });
                }
                else
                {
                    pd.cancel();
                    Toast.makeText(signUpp.this,  "Error Occured ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }
}