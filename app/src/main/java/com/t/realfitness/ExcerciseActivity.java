package com.t.realfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class ExcerciseActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    CardView Briskwalking, StationaryCycling, Swimming, TaiChi, Regularstretching, Yoga;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);

        //toolbar init
        toolbar = findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select Any Excercise");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);


        //navigation init
        navigationView=findViewById(R.id.navigationView);
        drawerLayout=findViewById(R.id.drawer_layout);


        //init cardview for excercise
        Briskwalking = findViewById(R.id.Briskwalking);
        StationaryCycling = findViewById(R.id.StationaryCycling);
        Swimming = findViewById(R.id.Swimming);
        TaiChi = findViewById(R.id.TaiChi);
        Regularstretching = findViewById(R.id.Regularstretching);
        Yoga = findViewById(R.id.Yoga);


        //set click listner on all card
        Briskwalking.setOnClickListener(this);
        StationaryCycling.setOnClickListener(this);
        Swimming.setOnClickListener(this);
        TaiChi.setOnClickListener(this);
        Regularstretching.setOnClickListener(this);
        Yoga.setOnClickListener(this);

        navigationView.setNavigationItemSelectedListener(this);

    }


    //open Counter activity after clicking on any excercise
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ExcerciseActivity.this, CounterActivity.class);
        switch (view.getId()) {
            case R.id.Briskwalking:
                intent.putExtra("key", "Briskwalking");
                break;

            case R.id.StationaryCycling:
                intent.putExtra("key", "StationaryCycling");
                break;

            case R.id.Swimming:
                intent.putExtra("key", "Swimming");
                break;

            case R.id.TaiChi:
                intent.putExtra("key", "TaiChi");
                break;

            case R.id.Regularstretching:
                intent.putExtra("key", "Regularstretching");
                break;

            case R.id.Yoga:
                intent.putExtra("key", "Yoga");
                break;

        }
        startActivity(intent);
    }


    //clik home button to open naigation drawer
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return true;

    }


    //click on naviation items to open new activity
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.todayHistory:
               Intent intent=new Intent(ExcerciseActivity.this,HistoryActivity.class);
               startActivity(intent);
                break;

            case R.id.allHistory:
                Intent intent1=new Intent(ExcerciseActivity.this,AllHistoryActivity.class);
                startActivity(intent1);
                break;


        }
        return false;
    }
}