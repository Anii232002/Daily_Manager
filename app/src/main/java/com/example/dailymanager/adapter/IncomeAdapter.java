package com.example.dailymanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dailymanager.CustomDialog;
import com.example.dailymanager.R;
import com.example.dailymanager.dataclass.DataItems;
import com.example.dailymanager.dataclass.DialogDetails;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {
    List<DataItems> items=new ArrayList<>();
    Context context;

    public IncomeAdapter(List<DataItems> items, Context context){
        this.items=items;
        this.context=context;
    }



    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.income_items,parent,false);

        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.IncomeViewHolder holder, int position) {
            DataItems icItems=items.get(position);

            holder.text.setText(icItems.getItem());
        Glide.with(context).load(icItems.getItemImages()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogDetails d=new DialogDetails();
                d.setCategory(icItems.getItem());
                d.setSection("income");
                CustomDialog cd=new CustomDialog(context);
                cd.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class IncomeViewHolder extends NotesAdapter.ViewHolder{
        ShapeableImageView image;
        TextView text;

        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.img_view);
            text=itemView.findViewById(R.id.category_text_view);
        }
    }

}
