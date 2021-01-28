package com.example.onlineopd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toolbar;

public class HomePage extends AppCompatActivity {
//Toolbar toolbar;
private int SPLASH_TIMINGS = 5000;  // 5 seconds
    Animation top,bottom;
    TextView welcome ;
//String TAG = "Mains";
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
//        toolbar = findViewById(R.id.tools);
        Intent intent  = getIntent();
     String a =    intent.getStringExtra("HEREITIS");

//        Log.d(TAG, a);
//     toolbar.setTitle(a);/
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
         top = AnimationUtils.loadAnimation(this,R.anim.top_animation);
         bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
         String c = "WELCOME " + a;
         welcome = findViewById(R.id.welcome);
         welcome.setText(c);
         welcome.setAnimation(top);
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent i = new Intent(HomePage.this,Homepage2.class);
                 startActivity(i);
                 finish();
             }
         },SPLASH_TIMINGS);
     }
}