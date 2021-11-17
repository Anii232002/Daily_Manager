package com.example.dailymanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dailymanager.adapter.IncomeListAdapter;
import com.example.dailymanager.BalanceCheckDatabase.BalanceSheetEntity;
import com.example.dailymanager.database.DataBaseEntity;
import com.example.dailymanager.database.ExpenseDataEntity;
import com.example.dailymanager.database.ExpensesViewModel;
import com.example.dailymanager.dataclass.DialogDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;



public class ExpenseHome extends Fragment {

    ExpensesViewModel expensesViewModel;
    private static final String INCOME_KEY="income_key";
    private static final String EXPENSE_KEY="expense_key";
    RecyclerView mainRecyclerView;
    BalanceSheetEntity b1;
    DataBaseEntity d;
    TextView expensesText;
    ExpenseDataEntity expenseDataEntity;
    TextView balanceText;
    TextView message;
    TextView incomeMain;




    IncomeListAdapter adapter;

    


    public ExpenseHome() {
        // Required empty public constructor

        super(R.layout.fragment_expense_home);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_expense_home, container, false);
        message=v.findViewById(R.id.message_text_view);
        expensesText=v.findViewById(R.id.expense_display);
        balanceText=v.findViewById(R.id.balance_text_view);

        adapter=new IncomeListAdapter(getActivity());

        expensesViewModel=new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(ExpensesViewModel.class);

        DialogDetails details=new DialogDetails();
        SharedPreferences shrd=getActivity().getSharedPreferences("demo",Context.MODE_PRIVATE);
        SharedPreferences expensesshrd=getActivity().getSharedPreferences("expenses",Context.MODE_PRIVATE);
        incomeMain=v.findViewById(R.id.income_text_view);


           if (details.getSection().equals("income"))
            d=new DataBaseEntity(details.getCategory(),details.getIncome());
        else
        expenseDataEntity=new ExpenseDataEntity(details.getExpense(), details.getCategory());


        b1=new BalanceSheetEntity(shrd.getInt(INCOME_KEY,0)+Integer.parseInt(details.getIncome()),expensesshrd.getInt(EXPENSE_KEY,0)+details.getExpense());


        if (details.getSection().equals("income") && !(d.getAmount()==null || d.getCategory()==null) && DialogDetails.isAdd()) {
            expensesViewModel.insert(d);
            DialogDetails.setAdd(false);


            SharedPreferences.Editor editor=shrd.edit();
            editor.putInt(INCOME_KEY,b1.getIncome());
            editor.apply();

            incomeMain.setText("+"+String.valueOf(shrd.getInt(INCOME_KEY,0)));
            b1.setBalance(shrd.getInt(INCOME_KEY,0)-expensesshrd.getInt(EXPENSE_KEY,0));
            balanceText.setText(String.valueOf(b1.getBalance()));

            //incomeMain.setText(String.valueOf(b1.getIncome()));
            Log.v("TAG",String.valueOf(b1.getIncome()));



        }
       else {
            incomeMain.setText("+"+String.valueOf(shrd.getInt(INCOME_KEY, 0)));
            balanceText.setText(String.valueOf(b1.getBalance()));
        }

       if ( details.getSection().equals("expense") && !(expenseDataEntity.getCategory()==null) && DialogDetails.isAdd()){
           expensesViewModel.insert(expenseDataEntity);
           DialogDetails.setAdd(false);

           SharedPreferences.Editor editor= expensesshrd.edit();

           editor.putInt(EXPENSE_KEY,b1.getExpense());
           editor.apply();

           expensesText.setText("-"+String.valueOf(expensesshrd.getInt(EXPENSE_KEY,0)));

           b1.setBalance(shrd.getInt(INCOME_KEY,0)-expensesshrd.getInt(EXPENSE_KEY,0));
           balanceText.setText(String.valueOf(b1.getBalance()));
       }
       else{
           expensesText.setText("-"+String.valueOf(expensesshrd.getInt(EXPENSE_KEY,0)));

           balanceText.setText(String.valueOf(b1.getBalance()));
       }





        expensesViewModel.getAllIncome().observe(getActivity(), new Observer<List<DataBaseEntity>>() {
            @Override
            public void onChanged(List<DataBaseEntity> dataBaseEntities) {
                adapter.setExpensesList(dataBaseEntities);

                if (!dataBaseEntities.isEmpty())
                message.setVisibility(View.GONE);
            }

        });

       expensesViewModel.getAllExpenses().observe(getActivity(), new Observer<List<ExpenseDataEntity>>() {
           @Override
           public void onChanged(List<ExpenseDataEntity> expenseDataEntities) {
               adapter.setList(expenseDataEntities);
           }
       });


              return v;
    }



    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mainRecyclerView=view.findViewById(R.id.main_recycler_view);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainRecyclerView.setAdapter(adapter);




        FloatingActionButton fab=view.findViewById(R.id.floating_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_expenseHome_to_fieldSelection);
            }
        });

    }
}