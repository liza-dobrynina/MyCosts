package com.example.mycosts.ui.categories;

import android.os.AsyncTask;

import com.example.mycosts.App;
import com.example.mycosts.db.MyCostsDatabase;
import com.example.mycosts.db.dao.CategoryDAO;
import com.example.mycosts.db.entities.Category;

import java.util.List;

public class AllCategoriesModel {

    public void getAllCategories(final GetAllCategoriesCallback callback) {
        GetAllCategoriesAsyncTask getAllCategoriesAsyncTask = new GetAllCategoriesAsyncTask(callback);
        getAllCategoriesAsyncTask.execute();
    }

    public void addCategory(Category category, CompleteCallback callback) {
        InsertCategoryAsyncTask insertCategoryAsyncTask = new InsertCategoryAsyncTask(callback);
        insertCategoryAsyncTask.execute(category);
    }

    public void deleteCategory(Category category, CompleteCallback callback) {
        DeleteCategoryAsyncTask deleteCategoryAsyncTask = new DeleteCategoryAsyncTask(callback);
        deleteCategoryAsyncTask.execute(category);
    }

    public void updateCategory(Category category, CompleteCallback callback) {
        UpdateCategoryAsyncTask updateCategoryAsyncTask = new UpdateCategoryAsyncTask(callback);
        updateCategoryAsyncTask.execute(category);
    }

    public interface GetAllCategoriesCallback {
        void onLoad(List<Category> categories);
    }

    public interface CompleteCallback {
        void onComplete();
    }

    static class InsertCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private final CompleteCallback callback;

        InsertCategoryAsyncTask(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Category... params) {
            MyCostsDatabase db = App.getInstance().getDatabase();
            CategoryDAO categoryDAO = db.categoryDAO();
            Category category = params[0];
            categoryDAO.insert(category);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    public static class GetAllCategoriesAsyncTask extends AsyncTask<Void, Void, List<Category>> {

        private final GetAllCategoriesCallback callback;

        public GetAllCategoriesAsyncTask(GetAllCategoriesCallback callback) {
            this.callback = callback;
        }

        @Override
        protected List<Category> doInBackground(Void... params) {
            MyCostsDatabase db = App.getInstance().getDatabase();
            CategoryDAO categoryDAO = db.categoryDAO();
            return categoryDAO.getAll();
        }

        @Override
        protected void onPostExecute(List<Category> result) {
            super.onPostExecute(result);
            if (callback != null) {
                callback.onLoad(result);
            }
        }
    }

    static class DeleteCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private final CompleteCallback callback;

        DeleteCategoryAsyncTask(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Category... params) {
            MyCostsDatabase db = App.getInstance().getDatabase();
            CategoryDAO categoryDAO = db.categoryDAO();
            Category category = params[0];
            categoryDAO.delete(category);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

    static class UpdateCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private final CompleteCallback callback;

        UpdateCategoryAsyncTask(CompleteCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Category... params) {
            MyCostsDatabase db = App.getInstance().getDatabase();
            CategoryDAO categoryDAO = db.categoryDAO();
            Category category = params[0];
            categoryDAO.update(category);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (callback != null) {
                callback.onComplete();
            }
        }
    }

}
