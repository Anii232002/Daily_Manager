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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.List;



public class ExpenseHome extends Fragment {

    public ExpensesViewModel expensesViewModel;
    private static final String INCOME_KEY="income_key";
    private static final String EXPENSE_KEY="expense_key";
    static final String ID_KEY="ID_Key";
    RecyclerView mainRecyclerView;
    BalanceSheetEntity b1;
    DataBaseEntity d;
    SharedPreferences shrd;
    SharedPreferences expensesshrd;
    int currentIncome,deletedIncome,currentExpense,deletedExpense;
    TextView expensesText;
    ExpenseDataEntity expenseDataEntity;
    TextView balanceText;
    int id1;
    int id2;
    List<DataBaseEntity> data=new ArrayList<>();
    List<ExpenseDataEntity> expenseData=new ArrayList<>();
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

        setHasOptionsMenu(true);
        expensesViewModel=new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(ExpensesViewModel.class);
        
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


        if (data.isEmpty() && expenseData.isEmpty()){
            message.setVisibility(View.VISIBLE);
        }
        DialogDetails details=new DialogDetails();

        shrd=getActivity().getSharedPreferences("demo",Context.MODE_PRIVATE);
         expensesshrd=getActivity().getSharedPreferences("expenses",Context.MODE_PRIVATE);
        incomeMain=v.findViewById(R.id.income_text_view);
        

           if (details.getSection().equals("income"))
            d=new DataBaseEntity(details.getCategory(),details.getIncome());
        else
        expenseDataEntity=new ExpenseDataEntity(details.getExpense(), details.getCategory());


        b1=new BalanceSheetEntity(shrd.getInt(INCOME_KEY,0)+Integer.parseInt(details.getIncome()),expensesshrd.getInt(EXPENSE_KEY,0)+details.getExpense());

//            incomeMain.setText(String.valueOf(shrd.getInt(INCOME_KEY,0)));
////            expensesText.setText(String.valueOf(expensesshrd.getInt(EXPENSE_KEY,0)));
            balanceText.setText(String.valueOf(shrd.getInt(INCOME_KEY,0)-expensesshrd.getInt(EXPENSE_KEY,0)));

        if (details.getSection().equals("income") && !(d.getAmount()==null || d.getCategory()==null) && DialogDetails.isAdd()) {
            expensesViewModel.insert(d);
            DialogDetails.setAdd(false);


            SharedPreferences.Editor editor=shrd.edit();
            editor.putInt(INCOME_KEY,b1.getIncome());
            editor.apply();

            incomeMain.setText(String.valueOf(shrd.getInt(INCOME_KEY,0)));
            b1.setBalance(shrd.getInt(INCOME_KEY,0)-expensesshrd.getInt(EXPENSE_KEY,0));
            balanceText.setText(String.valueOf(b1.getBalance()));

            //incomeMain.setText(String.valueOf(b1.getIncome()));
            Log.v("TAG",String.valueOf(b1.getIncome()));



        }
       else {
            incomeMain.setText(String.valueOf(shrd.getInt(INCOME_KEY, 0)));
            b1.setBalance(shrd.getInt(INCOME_KEY,0)-expensesshrd.getInt(EXPENSE_KEY,0));
            balanceText.setText(String.valueOf(b1.getBalance()));
        }

       if ( details.getSection().equals("expense") && !(expenseDataEntity.getCategory()==null) && DialogDetails.isAdd()){
           expensesViewModel.insert(expenseDataEntity);
           DialogDetails.setAdd(false);

           SharedPreferences.Editor editor= expensesshrd.edit();

           editor.putInt(EXPENSE_KEY,b1.getExpense());
           editor.apply();

           expensesText.setText(String.valueOf(expensesshrd.getInt(EXPENSE_KEY,0)));

           b1.setBalance(shrd.getInt(INCOME_KEY,0)-expensesshrd.getInt(EXPENSE_KEY,0));
           balanceText.setText(String.valueOf(b1.getBalance()));
       }
       else{
           expensesText.setText(String.valueOf(expensesshrd.getInt(EXPENSE_KEY,0)));
           b1.setBalance(shrd.getInt(INCOME_KEY,0)-expensesshrd.getInt(EXPENSE_KEY,0));
           balanceText.setText(String.valueOf(b1.getBalance()));
       }





        expensesViewModel.getAllIncome().observe(getActivity(), new Observer<List<DataBaseEntity>>() {
            @Override
            public void onChanged(List<DataBaseEntity> dataBaseEntities) {
                adapter.setExpensesList(dataBaseEntities);

                if (!dataBaseEntities.isEmpty())
                message.setVisibility(View.GONE);
                else
                    message.setVisibility(View.VISIBLE);
            }

        });

       expensesViewModel.getAllExpenses().observe(getActivity(), new Observer<List<ExpenseDataEntity>>() {
           @Override
           public void onChanged(List<ExpenseDataEntity> expenseDataEntities) {
               adapter.setList(expenseDataEntities);

               if (!expenseDataEntities.isEmpty()){
                   message.setVisibility(View.GONE);
               }

           }
       });

       adapter.setOnItemClickListener(new IncomeListAdapter.OnItemClickListener() {
           @Override
           public void getIncomeData(DataBaseEntity dataBaseEntity,View view) {
               id1=dataBaseEntity.getId();

               Navigation.findNavController(view).navigate(R.id.action_expenseHome_to_fieldSelection);

                     currentIncome = shrd.getInt(INCOME_KEY, 0);
                     deletedIncome = Integer.parseInt(dataBaseEntity.getAmount());


           }

           @Override
           public void getExpenseData(ExpenseDataEntity expenseDataEntity, View view) {
               id2=expenseDataEntity.getId();

               Navigation.findNavController(view).navigate(R.id.action_expenseHome_to_fieldSelection);
               currentExpense=expensesshrd.getInt(EXPENSE_KEY,0);
               deletedExpense=expenseDataEntity.getExpense();
           }

       });

       if (details.isIncomeUpdate() && details.getSection().equals("income")){
           SharedPreferences.Editor editor= shrd.edit();
           editor.putInt(INCOME_KEY, currentIncome - deletedIncome);
           editor.apply();
           DataBaseEntity d=new DataBaseEntity(details.getCategory(),details.getIncome());
           d.setId(id1);
           int currentIncome= shrd.getInt(INCOME_KEY,0);
           int changedIncome=Integer.parseInt(details.getIncome());
           expensesViewModel.update(d);



           editor.putInt(INCOME_KEY,currentIncome+changedIncome);
           editor.apply();

           incomeMain.setText(String.valueOf(shrd.getInt(INCOME_KEY,0)));
           details.setIncomeUpdate(false);

           b1.setBalance(shrd.getInt(INCOME_KEY,0)-expensesshrd.getInt(EXPENSE_KEY,0));
           balanceText.setText(String.valueOf(b1.getBalance()));
       }
       if(details.isIncomeUpdate() && details.getSection().equals("expense")){
           SharedPreferences.Editor editor= expensesshrd.edit();
           editor.putInt(EXPENSE_KEY,currentExpense-deletedExpense);
           editor.apply();

           ExpenseDataEntity e=new ExpenseDataEntity(details.getExpense(),details.getCategory());
           e.setId(id2);
           expensesViewModel.update(e);
           int currentExpense=expensesshrd.getInt(EXPENSE_KEY,0);
           int changedExpense= details.getExpense();


           editor.putInt(EXPENSE_KEY,currentExpense+changedExpense);
           editor.apply();
           expensesText.setText(String.valueOf(expensesshrd.getInt(EXPENSE_KEY,0)));
           details.setIncomeUpdate(false);

           b1.setBalance(shrd.getInt(INCOME_KEY,0)-expensesshrd.getInt(EXPENSE_KEY,0));
           balanceText.setText(String.valueOf(b1.getBalance()));
       }



              return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.expense_section_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteAll:
                expensesViewModel.deleteAll();
                expensesViewModel.deleteAllExpenses();
                SharedPreferences.Editor editorshrd=shrd.edit();
                SharedPreferences.Editor editorExpenses= expensesshrd.edit();

                editorshrd.putInt(INCOME_KEY,0);
                editorExpenses.putInt(EXPENSE_KEY,0);
                editorExpenses.apply();
                editorshrd.apply();

                incomeMain.setText("0");
                expensesText.setText("0");
                balanceText.setText("0");

                message.setVisibility(View.VISIBLE);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mainRecyclerView=view.findViewById(R.id.main_recycler_view);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainRecyclerView.setAdapter(adapter);



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                 data=expensesViewModel.getAllIncome().getValue();
                 expenseData=expensesViewModel.getAllExpenses().getValue();


                if (viewHolder instanceof IncomeListAdapter.IncomeListHolder){
                    expensesViewModel.delete(adapter.getDataAt(((IncomeListAdapter.IncomeListHolder)viewHolder).getAdapterPosition()));
                    SharedPreferences.Editor editor=shrd.edit();
                    int currentIncome= shrd.getInt(INCOME_KEY,0);
                    int deletedIncome=Integer.parseInt(data.get(((IncomeListAdapter.IncomeListHolder)viewHolder).getAdapterPosition()).getAmount());
                    editor.putInt(INCOME_KEY,currentIncome-deletedIncome);
                    editor.apply();
                    incomeMain.setText(String.valueOf(shrd.getInt(INCOME_KEY,0)));
                    b1.setBalance(shrd.getInt(INCOME_KEY,0)-expensesshrd.getInt(EXPENSE_KEY,0));
                    balanceText.setText(String.valueOf(b1.getBalance()));

                }
                if(viewHolder instanceof IncomeListAdapter.ExpensesListHolder){
                    expensesViewModel.delete(adapter.getExpenseAt(((IncomeListAdapter.ExpensesListHolder)viewHolder).getAdapterPosition()));
                    SharedPreferences.Editor editor=expensesshrd.edit();
                    int currentExpense=expensesshrd.getInt(EXPENSE_KEY,0);
                    int deletedExpense=expenseData.get(((IncomeListAdapter.ExpensesListHolder) viewHolder).getAdapterPosition()- data.size()).getExpense();
                    editor.putInt(EXPENSE_KEY,currentExpense-deletedExpense);
                    editor.apply();
                    expensesText.setText(String.valueOf(expensesshrd.getInt(EXPENSE_KEY,0)));
                    b1.setBalance(shrd.getInt(INCOME_KEY,0)-expensesshrd.getInt(EXPENSE_KEY,0));
                    balanceText.setText(String.valueOf(b1.getBalance()));
                }

                if (data.isEmpty() && expenseData.isEmpty())
                    message.setVisibility(View.VISIBLE);
            }
        }).attachToRecyclerView(mainRecyclerView);


        FloatingActionButton fab=view.findViewById(R.id.floating_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_expenseHome_to_fieldSelection);
            }
        });

    }
}