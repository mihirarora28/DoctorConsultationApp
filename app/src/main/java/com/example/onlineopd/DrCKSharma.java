package com.example.onlineopd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DrCKSharma extends AppCompatActivity {
    Button button;
    Button button2;
    TextView test;
    TextView test2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_c_k_sharma);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        test = findViewById(R.id.test);
        test2 = findViewById(R.id.test2);
        final String a = "tel:" + test.getText().toString();
        final String b = "mailto:" + test2.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (ActivityCompat.checkSelfPermission(DrCKSharma.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(b));

                startActivity(intent);
            }
        });

    }
}