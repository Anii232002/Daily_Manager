package com.example.dailymanager.adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dailymanager.R;
import com.example.dailymanager.dataclass.Note;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;



    public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

        Context context;
        ArrayList<Note> noteArrayList;   //arraylist of type note class which we created

        public NotesAdapter(Context context, ArrayList<Note> noteArrayList) {
            this.context = context;
            this.noteArrayList = noteArrayList;
        }

        @NonNull
        @Override
        public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false); //inflating item layout in viewholder
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
          Note note = noteArrayList.get(position);
          holder.title.setText(note.title);     // actually putting text in each textview
          holder.description.setText(note.description);
        }

        @Override
        public int getItemCount() {
            return noteArrayList.size();

        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView title,description;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);//initialising each variable with its id

                title = itemView.findViewById(R.id.title);
                description = itemView.findViewById(R.id.description);
                itemView.findViewById(R.id.itemdelete).setOnClickListener((View.OnClickListener) (new View.OnClickListener(){
                    public ArrayList<Note> noteArrayList;
                    final int position = NotesAdapter.ViewHolder.this.getAdapterPosition();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    @Override
                    public void onClick(View view) {
                        db.collection("notes").document()
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error deleting document", e);
                                    }
                                });

                    }


                }));
            }
        }
    }



