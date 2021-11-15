package com.example.dailymanager.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailymanager.R;
import com.example.dailymanager.BalanceCheckDatabase.BalanceSheetEntity;
import com.example.dailymanager.database.DataBaseEntity;
import com.example.dailymanager.dataclass.DialogDetails;

import java.util.ArrayList;
import java.util.List;

public class ExpensesListAdapter extends RecyclerView.Adapter<ExpensesListAdapter.ExpensesListHolder> {

      List<DataBaseEntity> expensesList=new ArrayList<>();
      List<BalanceSheetEntity> balanceList=new ArrayList<>();
    private static final String EXPENSE_KEY="expense_key";
     Context context;
//     TextView t;

     public ExpensesListAdapter(Context context) {
         this.context = context;
     }
//
//         View v=LayoutInflater.from(context).inflate(R.layout.fragment_expense_home,null);
////          t=v.findViewById(R.id.income_text_view);
//     }


    @Override
    public ExpensesListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.expenses_list,parent,false);

        return new ExpensesListHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull ExpensesListAdapter.ExpensesListHolder holder, int position) {

        DialogDetails d=new DialogDetails();

         if (d.getSection().equals("income")) {
             holder.categoryText.setText(expensesList.get(position).getCategory());
             holder.expenseText.setText("+" + expensesList.get(position).getAmount());
         }
         else{
             holder.categoryText.setText(expensesList.get(position).getCategory());
             holder.expenseText.setText("-" + expensesList.get(position).getAmount());
         }




        //.setText(String.valueOf(balanceList.get(position).getIncome()));


    }

    @Override
    public int getItemCount() {
        return expensesList.size();
    }

    public void setExpensesList(List<DataBaseEntity> expensesList){
        this.expensesList=expensesList;
        notifyDataSetChanged();
    }
//    public void setBalanceList(List<BalanceSheetEntity> balanceList){
//        this.balanceList=balanceList;
//        notifyDataSetChanged();
//    }
    public static class ExpensesListHolder extends NotesAdapter.ViewHolder{
            TextView categoryText;
            TextView expenseText;

        public ExpensesListHolder(@NonNull View itemView) {
            super(itemView);

            categoryText=itemView.findViewById(R.id.main_category_text_view);
            expenseText=itemView.findViewById(R.id.expenses_text_view);
        }
    }
}
