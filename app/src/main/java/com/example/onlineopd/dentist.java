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

public class dentist extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView ry2;
    String[] s1;
    String[] s2;
    String[] s3;
    String[] s4;
    adapterCardio.recycleViewClickListener listener;

    int[] images = {R.drawable.dentist1, R.drawable.dentist2, R.drawable.dentist3, R.drawable.dentist4, R.drawable.dentist5, R.drawable.dentist6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentist);

        toolbar = (Toolbar) findViewById(R.id.toolBarCardio);
//
        setSupportActionBar(toolbar);
//
//        toolbar.inflateMenu(R.menu.menu_menu);
        ry2 = findViewById(R.id.ry2);
        s1 = getResources().getStringArray(R.array.dentistnames);
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
                if(position == 0) {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dentist1);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent2 = new Intent(dentist.this, Dr_joshi.class);
                    intent2.putExtra("phonenumber", "+91-6868786580");
                    intent2.putExtra("email", "ajaymishra@gmail.com");
                    intent2.putExtra("name",s1[position]);
                    intent2.putExtra("picture", byteArray);
                    startActivity(intent2);
                  //  finish();
//                    Intent intent = new Intent(getApplicationContext(), Dr_joshi.class);
//                    intent.putExtra("resID", R.drawable.audiologist2);
//                    startActivity(intent);
                }
                if(position == 1)
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dentist2);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent2 = new Intent(dentist.this, Dr_joshi.class);
                    intent2.putExtra("phonenumber", "+919868786580");
                    intent2.putExtra("email", "amansharma@gmail.com");
                    intent2.putExtra("name",s1[position]);
                    intent2.putExtra("picture", byteArray);
                    startActivity(intent2);    // finish();
                }
                if(position ==2)
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dentist3);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent2 = new Intent(dentist.this, Dr_joshi.class);
                    intent2.putExtra("phonenumber", "+915868786580");
                    intent2.putExtra("email", "cksharma@gmail.com");
                    intent2.putExtra("name",s1[position]);
                    intent2.putExtra("picture", byteArray);
                    startActivity(intent2);    // finish();

                }
                if(position ==3)
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dentist4);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent2 = new Intent(dentist.this, Dr_joshi.class);
                    intent2.putExtra("phonenumber", "+919998786580");
                    intent2.putExtra("email", "kkarora@gmail.com");
                    intent2.putExtra("name",s1[position]);
                    intent2.putExtra("picture", byteArray);
                    startActivity(intent2);   //  finish();

                }
                if(position ==4)
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dentist5);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent2 = new Intent(dentist.this, Dr_joshi.class);
                    intent2.putExtra("phonenumber", "+918978786580");
                    intent2.putExtra("email", "neerajkumar@gmail.com");
                    intent2.putExtra("name",s1[position]);
                    intent2.putExtra("picture", byteArray);
                    startActivity(intent2);   //  finish();

                }
                if(position ==5)
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.dentist6);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent2 = new Intent(dentist.this, Dr_joshi.class);
                    intent2.putExtra("phonenumber", "+919999986580");
                    intent2.putExtra("email", "drjoshi@gmail.com");
                    intent2.putExtra("name",s1[position]);
                    intent2.putExtra("picture", byteArray);
                    startActivity(intent2);   //  finish();

                }
            }
        };
    }
}