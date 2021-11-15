package com.example.dailymanager.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IncomeDao {

    @Insert
    void insert(DataBaseEntity data);

    @Delete
    void delete(DataBaseEntity data);

    @Update
    void update(DataBaseEntity data);

    @Query("DELETE FROM income_table")
    void deleteAll();

    @Query("SELECT * FROM income_table ORDER BY amount DESC")
    LiveData<List<DataBaseEntity>> getAmount();

}
