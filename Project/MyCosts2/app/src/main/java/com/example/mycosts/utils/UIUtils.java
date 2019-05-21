package com.example.mycosts.utils;

import android.content.Context;
import android.widget.Spinner;

import com.example.mycosts.db.entities.Category;
import com.example.mycosts.ui.SpinnerAdapter;

import java.util.List;

public class UIUtils {
    public static void fillCategorySpinner(Context context, Spinner spinner, List<Category> categories) {
        Category[] data = new Category[categories.size()];
        categories.toArray(data);
        SpinnerAdapter adapter = new SpinnerAdapter(context, android.R.layout.simple_spinner_item, data);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    public static Integer getCategoryIndex(Spinner spinner, Long id) {
        android.widget.SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            Category item = (Category) adapter.getItem(i);
            if (item.getId().equals(id))
                return i;
        }
        return null;
    }
}
