package com.example.onlineopd;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class otpScreen2 extends AppCompatActivity {
    String verificationCodeSendBySystem;
    String TAG = "mainActivity";
    Button resendBtn;
    TextView counterTv;
    Button verificationBtn;
    TextView verifyTv;
//    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpscreen2);

//         mAuth = FirebaseAuth.getInstance();

        resendBtn = findViewById(R.id.resendBtn);
        counterTv = findViewById(R.id.counterTv);
        verificationBtn = findViewById(R.id.verificationBtn);
        verifyTv = findViewById(R.id.verifyTv);
        Intent intent = getIntent();
        String a = intent.getStringExtra("MIHIR");
        a = "+91" + a;
        verifyTv.setText(a);
      //  sendVerificationCodeToUser(a);
        String phone = verifyTv.getText().toString();
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                counterTv.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                resendBtn.setEnabled(true);
            }
        }.start();

        verificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(otpScreen2.this, Registration.class);
                startActivity(intent1);


            }
        });
//        //

//
        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
//
    }
//   val auth = FirebaseAuth.getInstance()
//        binding.fab.setOnClickListener {
//            val options = PhoneAuthOptions.newBuilder(auth)
//                .setPhoneNumber("+1 5123456788")
//                .setTimeout(60L, TimeUnit.SECONDS)
//                .setActivity(this)
//                .build()
//            PhoneAuthProvider.verifyPhoneNumber(options);
//        }
//
//val auth = FirebaseAuth.getInstance()
//    //        binding.fab.setOnClickListener {
////            val options = PhoneAuthOptions.newBuilder(auth)
////                .setPhoneNumber("+1 5123456788")
////                .setTimeout(60L, TimeUnit.SECONDS)
////                .setActivity(this)
////                .build()
//
        private void sendVerificationCodeToUser (String phoneNumber) {
//            PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                                phoneNumber,
//                                60,
//                    TimeUnit.SECONDS,
//                    otpScreen2.this,
//                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                    @Override
//                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//                                    }
//
//                                    @Override
//                                    public void onVerificationFailed(@NonNull FirebaseException e) {
//
//                                    }
//
//                                    @Override
//                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                        super.onCodeSent(s, forceResendingToken);
//                                    }
//                                }
//                        );
//
//            PhoneAuthOptions options =
//                    PhoneAuthOptions.newBuilder(mAuth)
//                            .setPhoneNumber(phoneNumber)       // Phone number to verify
//                            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                            .setActivity(this)                 // Activity (for callback binding)
//                            .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                            .build();
//            PhoneAuthProvider.verifyPhoneNumber(options);
//
//
//            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//                @Override
//                public void onVerificationCompleted(PhoneAuthCredential credential) {
//                    // This callback will be invoked in two situations:
//                    // 1 - Instant verification. In some cases the phone number can be instantly
//                    //     verified without needing to send or enter a verification code.
//                    // 2 - Auto-retrieval. On some devices Google Play services can automatically
//                    //     detect the incoming verification SMS and perform verification without
//                    //     user action.
//                    Log.d(TAG, "onVerificationCompleted:" + credential);
//
//                 //   signInWithPhoneAuthCredential(credential);
//                }
//
//                @Override
//                public void onVerificationFailed(FirebaseException e) {
//                    // This callback is invoked in an invalid request for verification is made,
//                    // for instance if the the phone number format is not valid.
//                    Log.w(TAG, "onVerificationFailed", e);
//
////                    eif (e instanceof FirebaseAuthInvalidCredentialsException) {
////                        // Invalid request
////                        // ...
////                    } else if (e instanceof FirebaseTooManyRequestsException) {
////                        // The SMS quota for the project has been exceeded
////                        // ...
////                    }
//
//                    // Show a message and update the UI
//                    // ...
//                }
//
//                @Override
//                public void onCodeSent(@NonNull String verificationId,
//                                       @NonNull PhoneAuthProvider.ForceResendingToken token) {
//                    // The SMS verification code has been sent to the provided phone number, we
//                    // now need to ask the user to enter the code and then construct a credential
//                    // by combining the code with a verification ID.
//                    Log.d(TAG, "onCodeSent:" + verificationId);
//
//                    // Save verification ID and resending token so we can use them later
////                    mVerificationId = verificationId;
////                    mResendToken = token;
//
//                    // ...
//                }
         //   };



// Whenever verification is triggered with the whitelisted number,
// provided it is not set for auto-retrieval, onCodeSent will be triggered.


//            FirebaseAuth mAuth = FirebaseAuth.getInstance();
//            mAuth.setLanguageCode("fr");
//            PhoneAuthOptions options =
//                    PhoneAuthOptions.newBuilder(mAuth)
//                            .setPhoneNumber(phoneNumber)       // Phone number to verify
//                            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                            .setActivity(this)                 // Activity (for callback binding)
//                            .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                            .build();
//            PhoneAuthProvider.verifyPhoneNumber(options);


        }
        
//            val auth = FirebaseAuth.getInstance()
//        binding.fab.setOnClickListener {
//            val options = PhoneAuthOptions.newBuilder(auth)
//                .setPhoneNumber("+1 5123456788")
//                .setTimeout(60L, TimeUnit.SECONDS)
//                .setActivity(this)
//                .build()



//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(mAuth)
//                        .setPhoneNumber(phoneNumber)       // Phone number to verify
//                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                        .setActivity(this)                 // Activity (for callback binding)
//                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                        .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);

//        private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            @Override
//            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                String code = phoneAuthCredential.getSmsCode();
//                if (code != null) {
//                    verifyCode(code);
//                }
//            }
//
//            @Override
//            public void onVerificationFailed(@NonNull FirebaseException e) {
//                Toast.makeText(otpScreen2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                super.onCodeSent(s, forceResendingToken);
//                verificationCodeSendBySystem = s;
//
//            }
//        };
//
//        private void verifyCode (String CodeByUser){
//            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeSendBySystem, CodeByUser);
//            signInUserCredentials(credential);
//        }
////
//        private void signInUserCredentials (PhoneAuthCredential credential){
//            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//
//
//            firebaseAuth.signInWithCredential(credential)
//                    .addOnCompleteListener(otpScreen2.this, new OnCompleteListener<AuthResult>() {
//
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                Intent intent = new Intent(getApplicationContext(), Registration.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                            } else {
//                                Toast.makeText(otpScreen2.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    });
//
//        }



}


