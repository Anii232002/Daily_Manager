package com.example.dailymanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailymanager.adapter.ExpenseAdapter;
import com.example.dailymanager.dataclass.DataItems;
import com.example.dailymanager.dataclass.DialogDetails;

import java.util.ArrayList;
import java.util.List;


public class ExpenseFragment extends Fragment {

        RecyclerView expenseRecyclerView;
        ExpenseAdapter expenseAdapter;
        List<DataItems> expenseItems=new ArrayList<>();


    public ExpenseFragment() {
        super(R.layout.fragment_expense);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_expense, container, false);

        expenseRecyclerView=view.findViewById(R.id.recycler_view_expense);

        expenseRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        expenseItems.add(new DataItems("Food",R.drawable.food));
        expenseItems.add(new DataItems("Bills",R.drawable.bill));
        expenseAdapter=new ExpenseAdapter(expenseItems,getContext());
        expenseRecyclerView.setAdapter(expenseAdapter);



                return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}