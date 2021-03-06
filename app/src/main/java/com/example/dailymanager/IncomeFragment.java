package com.example.dailymanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailymanager.adapter.IncomeAdapter;
import com.example.dailymanager.dataclass.DataItems;


import java.util.ArrayList;
import java.util.List;


public class IncomeFragment extends Fragment {

    RecyclerView incomeRecyclerView;
    IncomeAdapter adapter;
    List<DataItems> items=new ArrayList<>();



    public IncomeFragment() {
        // Required empty public constructor
        super(R.layout.fragment_income);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items.add(new DataItems("Salary",R.drawable.salary));
        items.add(new DataItems("Awards", R.drawable.awards));
        items.add(new DataItems("Invest",R.drawable.investement));
        items.add(new DataItems("Lottery",R.drawable.lottery));
        items.add(new DataItems("Coupons",R.drawable.coupon));
        items.add(new DataItems("Rental",R.drawable.house));
        items.add(new DataItems("Refunds",R.drawable.refunds));

//        NavController navController= Navigation.findNavController(view);


        incomeRecyclerView=view.findViewById(R.id.recycler_view_income);
        incomeRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        adapter=new IncomeAdapter(items,getContext(),getActivity());
        incomeRecyclerView.setAdapter(adapter);


    }
}