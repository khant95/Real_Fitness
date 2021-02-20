package com.t.realfitness.SQliteRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    //method to insert data in database
    @Insert
    public void insertData(Exercise exercise);


    //method to read data from database
    @Query("SELECT  * FROM  Exercise")
    List<Exercise> getAllData();


    //method to read one day data from database
    @Query("SELECT  * FROM  Exercise WHERE date LIKE :todayDate")
    List<Exercise> getData(String todayDate);


}
