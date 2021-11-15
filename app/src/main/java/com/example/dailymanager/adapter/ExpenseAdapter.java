package com.example.dailymanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dailymanager.CustomDialog;
import com.example.dailymanager.R;
import com.example.dailymanager.dataclass.DataItems;
import com.example.dailymanager.dataclass.DialogDetails;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    List<DataItems> expenseItems=new ArrayList<>();
    Context context;

    public ExpenseAdapter(List<DataItems> expenseItems, Context context){
        this.expenseItems=expenseItems;
        this.context=context;
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_items,parent,false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ExpenseViewHolder holder, int position) {
            DataItems dataItems=expenseItems.get(position);

            holder.text.setText(dataItems.getItem());
        Glide.with(context).load(dataItems.getItemImages()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogDetails d=new DialogDetails();
                d.setCategory(dataItems.getItem());
                d.setSection("expense");
                CustomDialog cd=new CustomDialog(context);
                cd.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return expenseItems.size();
    }

    public static class ExpenseViewHolder extends NotesAdapter.ViewHolder{
        ShapeableImageView image;
        TextView text;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.expense_img_view);
            text=itemView.findViewById(R.id.expense_category_text_view);
        }
    }
}
