package com.example.dailymanager.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {HabitsDataEntity.class}, version = 1, exportSchema = false)
public abstract class HabitDatabase extends RoomDatabase {

    public abstract HabitDao habitdao();

    private static volatile HabitDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static HabitDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HabitDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HabitDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}