package com.example.dailymanager.Habits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dailymanager.Expenses;
import com.example.dailymanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HabitsList extends AppCompatActivity {




    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_habit_list);


    }

    public void addHabit(View view) {
        Intent intent = new Intent(this, HabitsNew.class);
        startActivity(intent);
    }
}