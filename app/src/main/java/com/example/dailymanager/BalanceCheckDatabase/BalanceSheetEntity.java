package com.example.dailymanager.BalanceCheckDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class BalanceSheetEntity {


    int id;

    static int income;
    int expense;
    int balance;

    public BalanceSheetEntity(){}

    public BalanceSheetEntity(int income, int expense, int balance) {
        BalanceSheetEntity.income=income;
        this.expense =expense;
        this.balance =balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIncome() {
        return income;
    }

    public int getExpense() {
        return expense;
    }

    public int getBalance() {
        return balance;
    }
}
