package com.example.mycosts.ui.categories;

import android.view.View;

import com.example.mycosts.api.model.Category;

import java.util.List;

public interface CategoriesContract {
    void addNewCategory(View v);

    void setAdapter(List<Category> categories);

    void notifyItemRemoved(int position);

    void notifyItemChanged(int position);

    void changeData(List<Category> categories);
}
