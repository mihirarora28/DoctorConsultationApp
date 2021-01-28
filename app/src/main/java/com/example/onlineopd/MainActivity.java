package com.example.onlineopd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private int SPLASH_TIMINGS = 5000;  // 5 seconds
Animation top,bottom;
ImageView imageView;

TextView textView , textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        top = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        imageView = findViewById(R.id.images);
        textView = findViewById(R.id.text);
        textView2 = findViewById(R.id.text2);
        imageView.setAnimation(top);
        textView.setAnimation(top);
        textView2.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,otpScreen.class);
                startActivity(i);
                finish();
            }
        },SPLASH_TIMINGS);

    }
}