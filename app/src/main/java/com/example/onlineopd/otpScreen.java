package com.example.onlineopd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class otpScreen extends AppCompatActivity {
//    public int counter = 0;
//    @TargetApi(Build.VERSION_CODES.O)
 EditText myPhone ;
     Button nextBtn ;
     CountryCodePicker countryCodePicker;
     String ans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);

          myPhone = findViewById(R.id.phoneNumberEt);
          nextBtn = findViewById(R.id.nextBtn);
          countryCodePicker = findViewById(R.id.ccp);

        myPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                int count = s.length();
                if (count == 10) {
                    nextBtn.setEnabled(true);
                    ans =  myPhone.getText().toString();

                } else {
                    nextBtn.setEnabled(false);
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(otpScreen.this);
                builder.setTitle("ALERT");
                builder.setMessage("Are you sure you want to continue with number " + ans + " ?");
                builder.setNegativeButton("EDIT NUMBER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), otpScreen2.class);
                        i.putExtra("MIHIR", ans);
                        startActivity(i);
                    }
                });

                builder.show();
            }
        });
    }
}

