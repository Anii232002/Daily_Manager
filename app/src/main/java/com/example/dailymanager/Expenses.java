package com.example.dailymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.dailymanager.adapter.IncomeListAdapter;
import com.example.dailymanager.database.ExpensesViewModel;

public class Expenses extends AppCompatActivity {

    IncomeListAdapter adapter;
    ExpensesViewModel expensesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater=getMenuInflater();
//        menuInflater.inflate(R.menu.expense_section_menu,menu);
//        return true;
//    }


}