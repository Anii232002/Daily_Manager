package com.example.dailymanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dailymanager.dataclass.DialogDetails;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class BottomSheet extends BottomSheetDialogFragment {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;

    public BottomSheet() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bottom_sheet2, container, false);

        // Inflate the layout for this fragment


        TextView t1=view.findViewById(R.id.amount_text);
        b1= view.findViewById(R.id.btn_00);

        b2=view.findViewById(R.id.btn_01);
        b3=view.findViewById(R.id.btn_02);
        b4=view.findViewById(R.id.btn_10);
        b5=view.findViewById(R.id.btn_11);
        b6=view.findViewById(R.id.btn_12);
        b7=view.findViewById(R.id.btn_20);
        b8=view.findViewById(R.id.btn_21);
        b9=view.findViewById(R.id.btn_22);
        b10=view.findViewById(R.id.btn_03);
        b11=view.findViewById(R.id.btn_13);
        b12=view.findViewById(R.id.btn_23);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.append("1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.append("2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.append("3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.append("4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.append("5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.append("6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.append("7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.append("8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.append("9");
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.append("0");
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input=t1.getText().toString();
                DialogDetails details=new DialogDetails();



                if (details.isUpdate()){
                    details.setIncomeUpdate(true);
                    details.setUpdate(false);

                    if (details.getSection().equals("income"))
                        details.setIncome(input);
                    else
                        details.setExpense(Integer.parseInt(input));
                }
                else{
                    if (details.getSection().equals("income"))
                        details.setIncome(input);
                    else
                        details.setExpense(Integer.parseInt(input));
                    DialogDetails.setAdd(true);
                }


                dismiss();







            }

        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText("");
            }
        });
        return view;
    }
}