package com.example.dailymanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailymanager.Note;
import com.example.dailymanager.R;
import com.example.dailymanager.dataclass.Note;

import java.util.ArrayList;

public class NotesAdapter {

    public static class CustomAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

        Context context;
        ArrayList<Note> noteArrayList;   //arraylist of type note class which we created

        public CustomAdapter(Context context, ArrayList<Note> noteArrayList) {
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

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);                   //initialising each variable with its id
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }

}
