package com.t.realfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.t.realfitness.SQliteRoom.Exercise;
import com.t.realfitness.SQliteRoom.MyDatabaseClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //toolbar init
        toolbar=findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Today History");


        //recyclerview init
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Date date = new Date();

        //Current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String myDate = dateFormat.format(date);


        //read all history of exercises
        MyDatabaseClass database = Room.databaseBuilder(this, MyDatabaseClass.class, "exDatabase").allowMainThreadQueries().build();
        List<Exercise> todayData = database.myDao().getData(myDate);


        //set data to recyclerview
        adapter=new RecyclerViewAdapter(todayData);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(HistoryActivity.this, ExcerciseActivity.class));
        finish();
    }
}