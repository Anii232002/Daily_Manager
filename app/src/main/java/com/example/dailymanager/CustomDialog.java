package com.example.dailymanager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.dailymanager.database.DataBaseEntity;
import com.example.dailymanager.database.ExpensesViewModel;
import com.example.dailymanager.dataclass.DialogDetails;

public class CustomDialog extends Dialog {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
    TextView t1;
    private static final String ID_KEY="ID_Key";
    public CustomDialog(@NonNull Context context) {
        super(context);


        WindowManager.LayoutParams params = getWindow().getAttributes();

            params.gravity=Gravity.CENTER;
        setTitle(null);
        setCancelable(true);
        setOnCancelListener(null);

        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null);
        setContentView(view);
        t1=view.findViewById(R.id.amount_text);
       b1= view.findViewById(R.id.btn_00);

        b2=view.findViewById(R.id.btn_01);
        b3=view.findViewById(R.id.btn_02);
        b4=view.findViewById(R.id.btn_10);
       b5=view.findViewById(R.id.btn_11);
        b6=view.findViewById(R.id.btn_12);
        b7=view.findViewById(R.id.btn_20);
        b8=view.findViewById(R.id.btn_21);
        b9=view.findViewById(R.id.btn_22);
        b10=view.findViewById(R.id.btn_23);
       b11=view.findViewById(R.id.btn_24);
        b12=view.findViewById(R.id.btn_25);




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


      }

        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText("");
            }
        });





    }
}
