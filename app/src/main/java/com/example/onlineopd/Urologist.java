package com.example.onlineopd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import java.io.ByteArrayOutputStream;

public class Urologist extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView ry2;
    String[] s1;
    String[] s2;
    String[] s3;
    String[] s4;
    adapterCardio.recycleViewClickListener listener;

    int[] images = {R.drawable.urologist1, R.drawable.urologist2, R.drawable.urologist3, R.drawable.urologist4, R.drawable.urologist5, R.drawable.urologist6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urologist);

        toolbar = (Toolbar) findViewById(R.id.toolBarCardio);
//
        setSupportActionBar(toolbar);
//
//        toolbar.inflateMenu(R.menu.menu_menu);
        ry2 = findViewById(R.id.ry2);
        s1 = getResources().getStringArray(R.array.urologist);
        s2 = getResources().getStringArray(R.array.cardioExperience);
        s3 = getResources().getStringArray(R.array.cardioSurgeries);
        s4 = getResources().getStringArray(R.array.cardioLocation);
        setOnClickListener();
        adapterCardio ad = new adapterCardio(this, s1, s2, s3,s4,images,listener);
        ry2.setAdapter(ad);
        ry2.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setOnClickListener() {
        listener  = new adapterCardio.recycleViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                if(position == 0)
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.urologist1);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent2 = new Intent(Urologist.this, Dr_joshi.class);
                    intent2.putExtra("phonenumber", "+919868786580");
                    intent2.putExtra("email", "amansharma@gmail.com");
                    intent2.putExtra("name",s1[position]);
                    intent2.putExtra("picture", byteArray);
                    startActivity(intent2);
                }
                if(position == 1)
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.urologist2);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent2 = new Intent(Urologist.this, Dr_joshi.class);
                    intent2.putExtra("phonenumber", "+919868786580");
                    intent2.putExtra("email", "amansharma@gmail.com");
                    intent2.putExtra("name",s1[position]);
                    intent2.putExtra("picture", byteArray);
                    startActivity(intent2);
                }
                if(position == 2)
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.urologist3);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent2 = new Intent(Urologist.this, Dr_joshi.class);
                    intent2.putExtra("phonenumber", "+919868786580");
                    intent2.putExtra("email", "amansharma@gmail.com");
                    intent2.putExtra("name",s1[position]);
                    intent2.putExtra("picture", byteArray);
                    startActivity(intent2);
                }
                if(position == 3)
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.urologist4);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent2 = new Intent(Urologist.this, Dr_joshi.class);
                    intent2.putExtra("phonenumber", "+919868786580");
                    intent2.putExtra("email", "amansharma@gmail.com");
                    intent2.putExtra("name",s1[position]);
                    intent2.putExtra("picture", byteArray);
                    startActivity(intent2);
                }
                if(position == 4)
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.urologist5);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent2 = new Intent(Urologist.this, Dr_joshi.class);
                    intent2.putExtra("phonenumber", "+919868786580");
                    intent2.putExtra("email", "amansharma@gmail.com");
                    intent2.putExtra("name",s1[position]);
                    intent2.putExtra("picture", byteArray);
                    startActivity(intent2);
                }
                if(position == 5)
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.urologist6);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent2 = new Intent(Urologist.this, Dr_joshi.class);
                    intent2.putExtra("phonenumber", "+919868786580");
                    intent2.putExtra("email", "amansharma@gmail.com");
                    intent2.putExtra("name",s1[position]);
                    intent2.putExtra("picture", byteArray);
                    startActivity(intent2);
                }
            }
        };
    }
}