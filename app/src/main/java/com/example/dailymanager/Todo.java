package com.example.dailymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Todo<list> extends AppCompatActivity {

    private ArrayList<String> task = MainActivity.task;
    private ArrayAdapter<String> taskAdapter;
    private ListView listView;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);




        listView = findViewById(R.id.listView);

        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,task);
        listView.setAdapter(taskAdapter);
        bundle = getIntent().getExtras();
        String value = "";
        try {
             value = bundle.getString("com.example.dailymanager.Todo_Addition");
        }catch(Exception e){
        }
        if(!(value.equals("")) && value != null) {
                task.add(value);
        }
        setUpListViewListener();




        FloatingActionButton fab2 = findViewById(R.id.floatingActionButton2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Todo.this,Todo_Addition.class);
                Todo.this.startActivity(intent);
            }
        });
    }

    private void addTask() {
        bundle = getIntent().getExtras();
        String value = bundle.getString("com.example.dailymanager.Todo_Addition");
        SharedPreferences sp =getSharedPreferences("Todopref",MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("name",value);
        ed.apply();


        if(!(value.equals(""))) {
            taskAdapter.add(value);
        }
    }


    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Task Completed!!", Toast.LENGTH_SHORT).show();

                task.remove(i);
                taskAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}