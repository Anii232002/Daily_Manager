package com.example.dailymanager.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense_table")
public class ExpenseDataEntity {

    @PrimaryKey(autoGenerate = true)
    int id;

    int expense;
    String category;

    public ExpenseDataEntity(int expense, String category) {
        this.expense = expense;
        this.category = category;
    }

    public int getExpense() {
        return expense;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
