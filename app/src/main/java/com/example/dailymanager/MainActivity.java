package com.example.dailymanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dailymanager.adapter.NotesAdapter;
import com.example.dailymanager.dataclass.Note;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.Document;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


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
        Intent intent = new Intent(this,Habits.class);
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
