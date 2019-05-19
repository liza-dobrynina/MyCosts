package com.example.mycosts;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class UIUtils {
    public static void fillCategorySpinner(Context context, Spinner spinner) {
        List<String> categories = new ArrayList<>();
        for (Category category : App.getCategories()) {
            categories.add(category.getName());
        }
        String[] data = new String[categories.size()];
        categories.toArray(data);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, data);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

}
