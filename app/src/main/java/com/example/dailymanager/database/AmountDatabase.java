package com.example.dailymanager.database;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataBaseEntity.class,ExpenseDataEntity.class,HabitsDataEntity.class},version=2)
public abstract class AmountDatabase extends RoomDatabase {
    private static AmountDatabase instance;



    public abstract IncomeDao getIncomeDao();
    public abstract ExpenseDao getExpenseDao();
//    public abstract BalanceDao getBalanceDao();

    public static synchronized AmountDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context,AmountDatabase.class,"amount_database.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }




}
