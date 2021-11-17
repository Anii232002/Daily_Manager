package com.example.dailymanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailymanager.R;
import com.example.dailymanager.BalanceCheckDatabase.BalanceSheetEntity;
import com.example.dailymanager.database.DataBaseEntity;
import com.example.dailymanager.database.ExpenseDataEntity;
import com.example.dailymanager.dataclass.DialogDetails;

import java.util.ArrayList;
import java.util.List;

public class IncomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        final int VIEW_TYPE_INCOME=0;
        final int VIEW_TYPE_EXPENSE=1;
        View view;
        List<DataBaseEntity> expensesList=new ArrayList<>();
        List<ExpenseDataEntity> list=new ArrayList<>();
        Context context;


     public IncomeListAdapter(Context context) {
         this.context = context;
     }
//
//         View v=LayoutInflater.from(context).inflate(R.layout.fragment_expense_home,null);
////          t=v.findViewById(R.id.income_text_view);
//     }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         if (viewType==VIEW_TYPE_INCOME) {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenses_list, parent, false);
             return new IncomeListHolder(view);
         }

         if (viewType==VIEW_TYPE_EXPENSE){
             view=LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_layout_list,parent,false);
             return new ExpensesListHolder(view);
         }



        return null;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        DialogDetails d=new DialogDetails();

        if (holder instanceof IncomeListHolder){
            ((IncomeListHolder)holder).categoryText.setText(expensesList.get(position).getCategory());
            ((IncomeListHolder)holder).incomeText.setText("+"+expensesList.get(position).getAmount());
            ((IncomeListHolder)holder).incomeText.setTextColor(context.getResources().getColor(R.color.income_color));
        }

        if (holder instanceof ExpensesListHolder){
            ((ExpensesListHolder) holder).category.setText(list.get(position-expensesList.size()).getCategory());
            ((ExpensesListHolder) holder).expenseText.setText("-"+list.get(position- expensesList.size()).getExpense());
            ((ExpensesListHolder) holder).expenseText.setTextColor(context.getResources().getColor(R.color.expense_color));
        }







        //.setText(String.valueOf(balanceList.get(position).getIncome()));


    }

    @Override
    public int getItemCount() {
        return expensesList.size()+list.size();
    }

    @Override
    public int getItemViewType(int position) {


        if (position< expensesList.size()){
            return VIEW_TYPE_INCOME;
        }

        if (position-expensesList.size()< list.size()){
            return VIEW_TYPE_EXPENSE;
        }

        return -1;
    }

    public void setExpensesList(List<DataBaseEntity> expensesList){
        this.expensesList=expensesList;
        notifyDataSetChanged();
    }

    public void setList(List<ExpenseDataEntity> list){
         this.list=list;
         notifyDataSetChanged();
    }
//    public void setBalanceList(List<BalanceSheetEntity> balanceList){
//        this.balanceList=balanceList;
//        notifyDataSetChanged();
//    }

    public static class IncomeListHolder extends RecyclerView.ViewHolder {
            TextView categoryText;
            TextView incomeText;

        public IncomeListHolder(@NonNull View itemView) {
            super(itemView);

            categoryText=itemView.findViewById(R.id.main_category_text_view);
            incomeText=itemView.findViewById(R.id.expenses_text_view);
        }
    }

    public static class ExpensesListHolder extends RecyclerView.ViewHolder{

         TextView category;
         TextView expenseText;
        public ExpensesListHolder(@NonNull View itemView) {
            super(itemView);

            category=itemView.findViewById(R.id.main_category_text_view_2);
            expenseText=itemView.findViewById(R.id.expenses_text_view_2);
        }
    }


}
