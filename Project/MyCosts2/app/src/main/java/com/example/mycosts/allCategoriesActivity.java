package com.example.mycosts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

public class AllCategoriesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        ArrayList<Category> categories = (ArrayList<Category>) getIntent().getSerializableExtra(MainActivity.CATEGORIES);
        recyclerView = findViewById(R.id.recyclerView);
        AllCategoryAdapter allCategoryAdapter = new AllCategoryAdapter(AllCategoriesActivity.this, categories);
        recyclerView.setAdapter(allCategoryAdapter);
    }

    public void addNewCategoryClick(View v){

    }
    public void changeCategoryClick(View v){

    }

    public void deleteCategoryClick(View v){

    }



}
