package com.t.realfitness.SQliteRoom;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Exercise.class},version = 1)
public abstract class MyDatabaseClass extends RoomDatabase {
    public abstract MyDao myDao();
}
