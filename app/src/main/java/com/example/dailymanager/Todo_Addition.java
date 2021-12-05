package com.example.dailymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Todo_Addition extends AppCompatActivity {
    public static final String key= "com.example.dailymanager.Todo_Addition";
    private Button button;
    private EditText editTextTextPersonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_addition);

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.button3);
        button.setOnClickListener(view -> {

            String value = editTextTextPersonName.getText().toString();

            Intent intent = new Intent(Todo_Addition.this,Todo.class);

            if(!(value.equals(""))){
                intent.putExtra(key,value);
                Toast.makeText(Todo_Addition.this, "Task Added", Toast.LENGTH_SHORT).show();
                Todo_Addition.this.startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "Please Add task", Toast.LENGTH_SHORT).show();
            }

        });
    }
}