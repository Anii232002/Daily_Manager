package com.example.dailymanager.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert
    void insert(ExpenseDataEntity data);

    @Delete
    void delete(ExpenseDataEntity data);

    @Update
    void update(ExpenseDataEntity data);

    @Query("DELETE FROM expense_table")
    void deleteAll();

    @Query("SELECT * FROM expense_table ORDER BY expense DESC")
    LiveData<List<ExpenseDataEntity>> getAmount();
}
