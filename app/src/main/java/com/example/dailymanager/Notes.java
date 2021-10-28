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

import java.util.ArrayList;

public class Notes extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Note> notesArrayList;
    NotesAdapter notesAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(false);
        notesArrayList = new ArrayList<Note>();
        notesAdapter = new NotesAdapter(Notes.this,notesArrayList);
        recyclerView.setAdapter(notesAdapter);


        db = FirebaseFirestore.getInstance();



        EventChangeListener();
    }

    private void EventChangeListener() {

        db.collection("Notes")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        //onEvent method will be called whenever there is a change in data automatically
                        if(error!= null)
                        {

                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for(DocumentChange d : value.getDocumentChanges())
                        {
                            if(d.getType() == DocumentChange.Type.ADDED)
                            {  //if a new document is added , it will get added to the userArraylist
                                notesArrayList.add(d.getDocument().toObject(Note.class));
                            }
                            notesAdapter.notifyDataSetChanged();

                        }


                    }
                });
    }

    public void newnote(View view) {
            Intent intent = new Intent(this,NotesAddition.class);
            startActivity(intent);
    }
}