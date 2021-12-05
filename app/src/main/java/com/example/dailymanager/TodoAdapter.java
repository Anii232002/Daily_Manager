package com.example.dailymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private ArrayList<String> data;

    public TodoAdapter(ArrayList<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_task_layout,parent,false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int position) {
        String task = data.get(position);
        holder.text.setText(task);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textView3);
        }
    }
}
