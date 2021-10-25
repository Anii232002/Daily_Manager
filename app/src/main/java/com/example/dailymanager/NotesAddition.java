package com.example.dailymanager;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NotesAddition extends AppCompatActivity {
   private EditText title ;
   private EditText description ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_addition);

    }

    public void addnote(View view) {
        title = findViewById(R.id.edttitle);
        description = findViewById(R.id.edtdescription);
        String t = title.getText().toString();
       String d = description.getText().toString();

        // Add a new document with a generated id.
        Map<String, Object> data = new HashMap<>();
        data.put("title", t);
        data.put("description",d);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("notes")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        Toast.makeText(NotesAddition.this, "Note Added Successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,Notes.class);
        startActivity(i);
    }
}