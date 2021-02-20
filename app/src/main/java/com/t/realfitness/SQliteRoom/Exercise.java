package com.t.realfitness.SQliteRoom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {

    //model class for storing excercise history data in database
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String type;
    private String time;
    private String date;
    private String workoutTime;

    public Exercise(String type, String time, String date, String workoutTime) {
        this.type = type;
        this.time = time;
        this.date = date;
        this.workoutTime = workoutTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorkoutTime() {
        return workoutTime;
    }

    public void setWorkoutTime(String workoutTime) {
        this.workoutTime = workoutTime;
    }
}
