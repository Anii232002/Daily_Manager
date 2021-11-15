package com.example.dailymanager.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dailymanager.BalanceCheckDatabase.BalanceSheetEntity;

import java.util.List;

public class ExpensesViewModel extends AndroidViewModel {

    private ExpensesRepository expensesRepository;
    LiveData<List<DataBaseEntity>> allExpenses;
    LiveData<List<ExpenseDataEntity>> allBalance;

    public ExpensesViewModel(@NonNull Application application) {
        super(application);
        expensesRepository=new ExpensesRepository(application);
        allExpenses=expensesRepository.getAllAmount();
        allBalance= expensesRepository.getAllExpense();

    }

    public void insert(DataBaseEntity dataBaseEntity){
        expensesRepository.insert(dataBaseEntity);
    }
    public void insert(ExpenseDataEntity expenseDataEntity){
        expensesRepository.insert(expenseDataEntity);
    }

    public void delete(DataBaseEntity dataBaseEntity){
        expensesRepository.delete(dataBaseEntity);
    }public void update(DataBaseEntity dataBaseEntity){
        expensesRepository.update(dataBaseEntity);
    }public void deleteAll(){
        expensesRepository.deleteAll();
    }

    public LiveData<List<DataBaseEntity>> getAllIncome() {
        return allExpenses;
    }

    public LiveData<List<ExpenseDataEntity>> getAllExpenses() {
        return allBalance;
    }
}
