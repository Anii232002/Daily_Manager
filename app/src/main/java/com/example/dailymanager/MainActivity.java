package com.example.dailymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> task = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void expenses(View view) {
        Intent intent = new Intent(this,Expenses.class);
        startActivity(intent);
    }

    public void habits(View view) {
        Intent intent = new Intent(this, HabitsHome.class);
        startActivity(intent);
    }

    public void todo(View view) {
        Intent intent = new Intent(this,Todo.class);
        startActivity(intent);
    }

    public void notes(View view) {
        Intent intent = new Intent(this,Notes.class);
        startActivity(intent);
    }
}
