package com.example.mycosts;

import android.app.Application;

import java.util.List;

public class App extends Application {

    private static int expenseId = 0;
    private static List<Category> categories;

    public static List<Category> getCategories() {
        return categories;
    }

    public static int getNextExpenseId() {
        expenseId++;
        return expenseId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        categories = ModelUtils.fillCategories();
    }
}
