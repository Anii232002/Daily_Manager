package com.example.dailymanager.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "income_table")
public class DataBaseEntity {
    @PrimaryKey(autoGenerate = true)
    int id;


    String category;
    String amount;


    public DataBaseEntity(){}


    public DataBaseEntity(String category, String amount) {


            this.category = category;
            this.amount = amount;



    }



    public String getCategory() {
        return category;
    }

    public String getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
