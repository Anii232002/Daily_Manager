package com.example.dailymanager.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import com.example.dailymanager.BalanceCheckDatabase.BalanceSheetEntity;

import java.util.List;

public class ExpensesRepository {
    public IncomeDao incomeDao;
    public ExpenseDao expenseDao;

    LiveData<List<DataBaseEntity>> allAmount;
    LiveData<List<ExpenseDataEntity>> allExpense;

    public ExpensesRepository(Application application){
       AmountDatabase amountDatabase=AmountDatabase.getInstance(application);

       incomeDao=amountDatabase.getIncomeDao();
       expenseDao=amountDatabase.getExpenseDao();
       allAmount=incomeDao.getAmount();
        allExpense=expenseDao.getAmount();
    }

    public void insert(DataBaseEntity dataBaseEntity){
        new InsertAsyncTask(incomeDao).execute(dataBaseEntity);
    }

    public void insert(ExpenseDataEntity expenseDataEntity){
        new InsertExpenseAsyncTask(expenseDao).execute(expenseDataEntity);
    }

    public void update(DataBaseEntity dataBaseEntity){
        new UpdateAsyncTask(incomeDao).execute(dataBaseEntity);
    }

    public void update(ExpenseDataEntity expenseDataEntity){
        new UpdateExpenseAsyncTask(expenseDao).execute(expenseDataEntity);
    }
    public void delete(DataBaseEntity dataBaseEntity){
        new DeleteAsyncTask(incomeDao).execute(dataBaseEntity);
    }
    public void delete(ExpenseDataEntity expenseDataEntity){
        new DeleteExpenseAsyncTask(expenseDao).execute(expenseDataEntity);
    }

    public void deleteAll(){
        new DeleteAllAsyncTask(incomeDao).execute();
    }
    public void deleteExpenseAll(){
        new DeleteAllExpensesAsyncTask(expenseDao).execute();
    }

    private static class InsertAsyncTask extends AsyncTask<DataBaseEntity,Void,Void>{
        IncomeDao incomeDao;

        public InsertAsyncTask(IncomeDao expensesDao){
            this.incomeDao=expensesDao;
        }

        @Override
        protected Void doInBackground(DataBaseEntity... dataBaseEntities) {
            incomeDao.insert(dataBaseEntities[0]);
            return null;
        }


    }

    private static class DeleteAsyncTask extends AsyncTask<DataBaseEntity,Void,Void>{
        IncomeDao incomeDao;

        public DeleteAsyncTask(IncomeDao incomeDao){
            this.incomeDao=incomeDao;
        }

        @Override
        protected Void doInBackground(DataBaseEntity... dataBaseEntities) {
            incomeDao.delete(dataBaseEntities[0]);
            return null;
        }


    }

    private static class UpdateAsyncTask extends AsyncTask<DataBaseEntity,Void,Void>{
        IncomeDao incomeDao;

        public UpdateAsyncTask(IncomeDao incomeDao){
            this.incomeDao=incomeDao;
        }

        @Override
        protected Void doInBackground(DataBaseEntity... dataBaseEntities) {
            incomeDao.update(dataBaseEntities[0]);
            return null;
        }


    }
    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        IncomeDao incomeDao;

        public DeleteAllAsyncTask(IncomeDao incomeDao){
            this.incomeDao=incomeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            incomeDao.deleteAll();
            return null;
        }
    }
    private static class DeleteAllExpensesAsyncTask extends AsyncTask<Void,Void,Void>{
        ExpenseDao expenseDao;

        public DeleteAllExpensesAsyncTask(ExpenseDao expenseDao){
            this.expenseDao=expenseDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            expenseDao.deleteAll();
            return null;
        }
    }

    public static class InsertExpenseAsyncTask extends AsyncTask<ExpenseDataEntity,Void,Void>{
        ExpenseDao expenseDao;

        public InsertExpenseAsyncTask(ExpenseDao expenseDao){
            this.expenseDao=expenseDao;
        }


        @Override
        protected Void doInBackground(ExpenseDataEntity... expenseDataEntities) {
            expenseDao.insert(expenseDataEntities[0]);
            return null;
        }
    }

    public static class DeleteExpenseAsyncTask extends AsyncTask<ExpenseDataEntity,Void,Void> {
        ExpenseDao expenseDao;

        public DeleteExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(ExpenseDataEntity... expenseDataEntities) {
            expenseDao.delete(expenseDataEntities[0]);
            return null;
        }
    }

        public static class UpdateExpenseAsyncTask extends AsyncTask<ExpenseDataEntity,Void,Void>{
            ExpenseDao expenseDao;

            public UpdateExpenseAsyncTask(ExpenseDao expenseDao){
                this.expenseDao=expenseDao;
            }
            @Override
            protected Void doInBackground(ExpenseDataEntity... expenseDataEntities) {
                expenseDao.update(expenseDataEntities[0]);
                return null;
            }


    }



    public LiveData<List<DataBaseEntity>> getAllAmount() {
        return allAmount;
    }

    public LiveData<List<ExpenseDataEntity>> getAllExpense() {
        return allExpense;
    }
}
