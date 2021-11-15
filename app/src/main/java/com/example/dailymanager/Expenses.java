package com.example.dailymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.dailymanager.adapter.ExpensesListAdapter;
import com.example.dailymanager.database.DataBaseEntity;
import com.example.dailymanager.database.ExpensesViewModel;
import com.example.dailymanager.dataclass.DialogDetails;

import java.util.List;

public class Expenses extends AppCompatActivity {

    ExpensesListAdapter adapter;
    ExpensesViewModel expensesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);


    }
}