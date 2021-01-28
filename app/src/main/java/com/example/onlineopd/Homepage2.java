package com.example.onlineopd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineopd.ui.login.Users;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Homepage2 extends AppCompatActivity  {
    Toolbar toolbar;
    RecyclerView ry1;
    String[] s1;
    String[] s2;
    adapter.recycleViewClickListener listener;
    int[] images = {R.drawable.cardiologist, R.drawable.dentist, R.drawable.audiologist, R.drawable.orthopaedic, R.drawable.radiologist, R.drawable.paedaetician, R.drawable.kidney};
    adapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage2);

//        if(FirebaseAuth.getInstance().getCurrentUser() == null)
//        {
//            Intent intent = new Intent(this,Registration.class);
//            startActivity(intent);
//            finish();
//        }
     //   editText = findViewById(R.id.editText);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);

//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//
//            }
//        });

        setSupportActionBar(toolbar);

//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE-dd/MM/yyyy");
//        String currentTime = simpleDateFormat.format(calendar.getTime());



//        currentTimelistView = findViewById(R.id.lv1);


        ry1 = findViewById(R.id.recycle1);
        s1 = getResources().getStringArray(R.array.doctors);

        s2 = getResources().getStringArray(R.array.description);
        List<Users> list = new ArrayList<Users>();
        for(int i = 0 ; i<s1.length ; i++)
        {
            Users abc = new Users(s1[i],images[i]); 
            list.add(abc);
        }

        setOnClickListener(); 
       ad = new adapter(this,list,listener);

        ry1.setAdapter(ad);
        ry1.setLayoutManager(new LinearLayoutManager(this));

        toolbar.inflateMenu(R.menu.menu_menu);

//     MyAdap myAdap = new MyAdap(this, title,images);
//     listView.setAdapter(myAdap);


      //Adapters;
    }

    private void filter(String toString) {
        ArrayList<adapter> filtered = new ArrayList<>();
    }

    private void setOnClickListener() {
        listener  = new adapter.recycleViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                if(position == 0) {
                    Intent intent = new Intent(getApplicationContext(), Cardiologists.class);
                    startActivity(intent);
                }   if(position == 1) {
                    Intent intent = new Intent(getApplicationContext(), dentist.class);
                    startActivity(intent);
                }
                if(position == 2) {
                    Intent intent = new Intent(getApplicationContext(), Audiologist.class);
                    startActivity(intent);
                }
                if(position == 3) {
                    Intent intent = new Intent(getApplicationContext(), Orthopaedic.class);
                    startActivity(intent);
                }
                if(position == 4) {
                    Intent intent = new Intent(getApplicationContext(), Neurologist.class);
                    startActivity(intent);
                }
                if(position == 5) {
                    Intent intent = new Intent(getApplicationContext(), Paediatrician.class);
                    startActivity(intent);
                }
                if(position == 6) {
                    Intent intent = new Intent(getApplicationContext(), Urologist.class);
                    startActivity(intent);
                }

            }
        };
    }

    //    class MyAdap extends ArrayAdapter<String>{
//
//        Context context;
//        String[] s1;
//        int[] rimages;
//
//        MyAdap(Context c, String tits[], int img[])
//        {
//
//             super(c,R.layout.myrows, R.id.txt1, tits);
//            this.context = c;
//            this.s1 = tits;
//            this.rimages = img;
//
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View row = layoutInflater.in
//            flate(R.layout.myrows, parent,false);
//            ImageView imaggges = row.findViewById(R.id.imagg);
//            TextView tteextt = row.findViewById(R.id.txt1);
//            imaggges.setImageResource(rimages[position]);
//            tteextt.setText(s1[position]);
//
//            return super.getView(position, convertView, parent);
//        }
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ad.getFilter().filter(newText.toString());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {       case R.id.one:
                Intent intent1 = new Intent(Homepage2.this, EditProfile.class);
                startActivity(intent1);
                break;

            case R.id.two:
                Intent intent2 = new Intent(Homepage2.this, About.class);
                startActivity(intent2);
                break;

            case R.id.three:
                Intent intent3 = new Intent(Homepage2.this, HistoryActivity.class);
                startActivity(intent3);
                break;

            case R.id.four:
                Toast.makeText(Homepage2.this, "See You Soon", Toast.LENGTH_SHORT).show();
                FirebaseAuth firebaseAuth;
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Homepage2.this, Registration.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    //    @Override
//    public void onNoteClick(int position) {
//
//    }
}