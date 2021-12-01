package com.example.dailymanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class EnterData extends Fragment {



    public EnterData() {
       super(R.layout.fragment_enter_data);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_enter_data, container, false);

        TextView amount=view.findViewById(R.id.amount_text);

        amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               BottomSheetFragment bottomSheetFragment=new BottomSheetFragment();
               bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });
        return view;
    }
}