package com.example.mycosts;

import android.app.Application;

import java.util.ArrayList;

public class App extends Application {

    private static ArrayList<Category> categories;

    @Override
    public void onCreate() {
        super.onCreate();
        fillCategories();
    }

    private void fillCategories() {
        categories = new ArrayList<>();
        categories.add(new Category("Еда", 1000));
        categories.add(new Category("Развлечения", 5000));
        categories.add(new Category("Всякое", 500));
    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }
}
