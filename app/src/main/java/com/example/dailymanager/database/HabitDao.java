package com.example.dailymanager.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addHabit(HabitsDataEntity habit);

    @Update
    void updateHabit(HabitsDataEntity habit);

    @Delete
    void deleteHabit(HabitsDataEntity habit);

    @Query("DELETE FROM habits_table")
    void deleteAll();

    @Query("SELECT * FROM habits_table ORDER BY id DESC")
    LiveData<List<HabitsDataEntity>> getAllHabit();
}
