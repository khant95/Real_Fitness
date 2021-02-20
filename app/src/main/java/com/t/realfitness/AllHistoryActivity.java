package com.t.realfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.t.realfitness.SQliteRoom.Exercise;
import com.t.realfitness.SQliteRoom.MyDatabaseClass;

import java.util.List;

public class AllHistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_history);


        //set toolbar name title
        toolbar=findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All History");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        //read excercise data from Room
        MyDatabaseClass database = Room.databaseBuilder(this, MyDatabaseClass.class, "exDatabase").allowMainThreadQueries().build();
        List<Exercise> todayData = database.myDao().getAllData();


        //set data to recyclerview
        adapter=new RecyclerViewAdapter(todayData);
        recyclerView.setAdapter(adapter);

    }
}