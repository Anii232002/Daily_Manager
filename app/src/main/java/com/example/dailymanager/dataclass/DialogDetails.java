package com.example.dailymanager.dataclass;

import android.app.Activity;
import android.app.Application;

import com.example.dailymanager.database.ExpensesViewModel;

public class DialogDetails {
    static String income="0";
    static boolean add=false;
    static int expense=0;
    static String section= "";
    static boolean update=false;
    static boolean incomeUpdate=false;


    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        DialogDetails.update = update;
    }



    public boolean isIncomeUpdate() {
        return incomeUpdate;
    }

    public void setIncomeUpdate(boolean incomeUpdate) {
        DialogDetails.incomeUpdate = incomeUpdate;
    }

    public String getSection() {
        return section;
    }



    public void setSection(String section) {
        DialogDetails.section = section;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        DialogDetails.expense = expense;
    }

    public static boolean isAdd() {
        return add;
    }

    public static void setAdd(boolean add) {
        DialogDetails.add = add;
    }

    static String category;

    static String memo;

    public  void setIncome(String income) {
        DialogDetails.income = income;
    }

    public  void setCategory(String category) {
        DialogDetails.category = category;
    }

    public  void setMemo(String memo) {
        DialogDetails.memo = memo;
    }

    public  String getIncome() {
        return income;
    }

    public  String getCategory() {
        return category;
    }

    public  String getMemo() {
        return memo;
    }
}
