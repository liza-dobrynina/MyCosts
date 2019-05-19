package com.example.mycosts;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AllCategoriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton addCategory;
    private List<Category> categories = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_categories, container, false);
        categories = App.getCategories();
        recyclerView = view.findViewById(R.id.recyclerView);
        addCategory = view.findViewById(R.id.addCategory);
        if (getActivity() != null) {
            AllCategoryAdapter allCategoryAdapter = new AllCategoryAdapter(getContext(), categories);
            recyclerView.setAdapter(allCategoryAdapter);
        }

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCategoryClick(v);
            }

        });
        return view;
    }

    public void addNewCategoryClick(View v) {
        if (getActivity() != null) {
            final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
            dialog.setTitle("Добавить категорию");
            dialog.setContentView(R.layout.dialog_category);
            final EditText categoryName = dialog.findViewById(R.id.categoryName);
            final EditText categoryMaxSum = dialog.findViewById(R.id.categoryMaxSum);
            Button saveButton = dialog.findViewById(R.id.saveButton);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = categoryName.getText().toString();
                    if (!name.isEmpty()) {
                        int maxSum = !categoryMaxSum.getText().toString().isEmpty() ?
                                Integer.parseInt(categoryMaxSum.getText().toString()) :
                                0;
                        Category category = new Category(name, maxSum);
                        categories.add(category);
                    }
                    dialog.cancel();
                }
            });

            dialog.show();
        }
    }
}
