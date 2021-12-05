package com.example.dailymanager.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.dailymanager.Habits.Calc;
import com.example.dailymanager.R;
import com.example.dailymanager.database.HabitsDataEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;


public final class HabitListAdapter extends Adapter {
    private final OnItemClickListener listener;

    public HabitListAdapter(List<HabitsDataEntity> items, OnItemClickListener listener) {
        this.habitsList = items;
        this.listener = listener;
    }



    @NotNull
    private List<HabitsDataEntity> habitsList = new ArrayList<>();

    public HabitListAdapter(OnItemClickListener listener) {

        this.listener = listener;
    }

    @NotNull
    public final List getHabitsList() {
        return this.habitsList;
    }

    public final void setHabitsList(@NotNull List var1) {

        this.habitsList = var1;
    }

    @NotNull
    public HabitListAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View var10003 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_habit_item, parent, false);

        return new HabitListAdapter.MyViewHolder(var10003);
    }




    public void onBindViewHolder(@NotNull HabitListAdapter.MyViewHolder holder, int position) throws ParseException {

        HabitsDataEntity currentHabit =  this.habitsList.get(position);
        holder.title.setText(currentHabit.getH_title());
        holder.description.setText(currentHabit.getH_description());
        holder.habiticon.setImageResource(currentHabit.getImageid());
        holder.timestamp.setText("Started: "+currentHabit.getH_timestamp()+" ");
        holder.elapsedtime.setText(Calc.INSTANCE.calculateTimeBetweenDates(currentHabit.getH_timestamp()));


    }

    // $FF: synthetic method
    // $FF: bridge method
    public void onBindViewHolder(ViewHolder var1, int var2) {
        try {
            this.onBindViewHolder((MyViewHolder)var1, var2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getItemCount() {
        return this.habitsList.size();
    }

    public final void setData(@NotNull List habit) {
        Intrinsics.checkNotNullParameter(habit, "habit");
        this.habitsList = habit;
        this.notifyDataSetChanged();
    }


    public final class MyViewHolder extends ViewHolder {
        TextView title;
        TextView description;
        TextView timestamp;
        TextView elapsedtime;
        ImageView habiticon;
        ImageButton deletebtn;
        public MyViewHolder(@NotNull final View itemView) {

            super(itemView);
            itemView.findViewById(R.id.itemdelete).setOnClickListener((OnClickListener) (new OnClickListener(){
                final int position = MyViewHolder.this.getAdapterPosition();
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(habitsList.get(getAdapterPosition()));

                }
            }));
            ((CardView)itemView.findViewById(R.id.cv_cardView)).setOnClickListener((OnClickListener)(new OnClickListener() {
                public final void onClick(View it) {
                  int position = MyViewHolder.this.getAdapterPosition();
                    Log.d("HabitsListAdapter", "Item clicked at: " + position);


             /*     NavDirections  action =
                            HabitListDirections
                                    .actionHabitListToHabitsUpdate(habitsList.get(position));
                    Navigation.findNavController(it).navigate(action);*/



                }
            }));

            title =  itemView.findViewById(R.id.tv_item_title);
            description = itemView.findViewById(R.id.tv_item_description);
            timestamp = itemView.findViewById(R.id.tv_item_createdTimeStamp);
            elapsedtime = itemView.findViewById(R.id.tv_timeElapsed);
            habiticon = itemView.findViewById(R.id.iv_habit_icon);
            deletebtn = itemView.findViewById(R.id.itemdelete);
        }
    }
}

