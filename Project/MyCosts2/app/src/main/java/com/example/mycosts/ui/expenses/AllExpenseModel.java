package com.example.mycosts.ui.expenses;

import android.os.AsyncTask;

import com.example.mycosts.App;
import com.example.mycosts.db.MyCostsDatabase;
import com.example.mycosts.db.dao.CategoryDAO;
import com.example.mycosts.db.dao.ExpenseDAO;
import com.example.mycosts.db.entities.Category;
import com.example.mycosts.db.entities.CategorySum;
import com.example.mycosts.db.entities.Expense;
import com.example.mycosts.db.entities.ExpenseWithCategory;
import com.example.mycosts.ui.categories.AllCategoriesModel;

import java.util.List;

public class AllExpenseModel {

    public void getAllExpenses(GetAllExpensesCallback callback) {
        GetAllExpensesAsyncTasks getAllExpensesAsyncTasks = new GetAllExpensesAsyncTasks(callback);
        getAllExpensesAsyncTasks.execute();
    }

    public void insertExpense(Expense expense, AllCategoriesModel.CompleteCallback callback) {
        InsertExpenseAsyncTask insertExpenseAsyncTask = new InsertExpenseAsyncTask(callback);
        insertExpenseAsyncTask.execute(expense);
    }

    public void getAllCategories(AllCategoriesModel.GetAllCategoriesCallback callback) {
        AllCategoriesModel.GetAllCategoriesAsyncTask getAllCategoriesAsyncTask = new AllCategoriesModel.GetAllCategoriesAsyncTask(callback);
        getAllCategoriesAsyncTask.execute();
    }

    public void updateExpense(Expense expense, AllCategoriesModel.CompleteCallback callback) {
        UpdateExpenseAsyncTask updateExpenseAsyncTask = new UpdateExpenseAsyncTask(callback);
        updateExpenseAsyncTask.execute(expense);
    }

    public void deleteExpense(Expense expense, AllCategoriesModel.CompleteCallback callback) {
        DeleteExpenseAsyncTask deleteExpenseAsyncTask = new DeleteExpenseAsyncTask(callback);
        deleteExpenseAsyncTask.execute(expense);
    }

    public void getAllExpenseSum(Long categoryId, GetAllExpenseSumByCategoryCallback callback){
        GetSumAllExpenseAsyncTask getSumAllExpenseAsyncTask = new GetSumAllExpenseAsyncTask(callback);
        getSumAllExpenseAsyncTask.execute(categoryId);
    }

    interface GetAllExpensesCallback {
        void onLoad(List<ExpenseWithCategory> expenses);
    }

    interface GetAllExpenseSumByCategoryCallback {
        void onLoad(CategorySum sum);
    }

    static class GetAllExpensesAsyncTasks extends AsyncTask<Void, Void, List<ExpenseWithCategory>> {
        private GetAllExpensesCallback callback;

        public GetAllExpensesAsyncTasks(GetAllExpensesCallback callback) {
            this.callback = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<ExpenseWithCategory> expenses) {
            super.onPostExecute(expenses);
            if (callback != null)
                callback.onLoad(expenses);
        }

        @Override
        protected List<ExpenseWithCategory> doInBackground(Void... voids) {
            MyCostsDatabase myCostsDatabase = App.getInstance().getDatabase();
            ExpenseDAO expenseDAO = myCostsDatabase.expenseDAO();
            List<ExpenseWithCategory> expenses = expenseDAO.getAllExpensesWithCategory();
            return expenses;
        }
    }

    static class InsertExpenseAsyncTask extends AsyncTask<Expense, Void, Void> {

        private AllCategoriesModel.CompleteCallback callback;

        public InsertExpenseAsyncTask(AllCategoriesModel.CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null) {
                callback.onComplete();
            }
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            MyCostsDatabase myCostsDatabase = App.getInstance().getDatabase();
            ExpenseDAO expenseDAO = myCostsDatabase.expenseDAO();
            Expense expense = expenses[0];
            expenseDAO.insert(expense);
            return null;
        }
    }

    static class DeleteExpenseAsyncTask extends AsyncTask<Expense, Void, Void> {

        private AllCategoriesModel.CompleteCallback callback;

        DeleteExpenseAsyncTask(AllCategoriesModel.CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null) {
                callback.onComplete();
            }
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            MyCostsDatabase myCostsDatabase = App.getInstance().getDatabase();
            ExpenseDAO expenseDAO = myCostsDatabase.expenseDAO();
            Expense expense = expenses[0];
            expenseDAO.delete(expense);
            return null;
        }
    }

    static class UpdateExpenseAsyncTask extends AsyncTask<Expense, Void, Void> {

        private AllCategoriesModel.CompleteCallback callback;

        UpdateExpenseAsyncTask(AllCategoriesModel.CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null)
                callback.onComplete();
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            MyCostsDatabase myCostsDatabase = App.getInstance().getDatabase();
            ExpenseDAO expenseDAO = myCostsDatabase.expenseDAO();
            Expense expense = expenses[0];
            expenseDAO.update(expense);
            return null;
        }
    }

    static class GetSumAllExpenseAsyncTask extends AsyncTask<Long, Void, CategorySum> {
        GetAllExpenseSumByCategoryCallback callback;

        GetSumAllExpenseAsyncTask(GetAllExpenseSumByCategoryCallback callback) {
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(CategorySum sum) {
            super.onPostExecute(sum);
            if (callback != null) {
                callback.onLoad(sum);
            }
        }

        @Override
        protected CategorySum doInBackground(Long... longs) {
            MyCostsDatabase database = App.getInstance().getDatabase();
            ExpenseDAO expenseDAO = database.expenseDAO();
            CategorySum categorySum = expenseDAO.getExpenseSumByCategory(longs[0]);
            return categorySum;
        }
    }
}
