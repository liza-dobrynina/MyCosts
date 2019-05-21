package com.example.mycosts.ui.categories;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mycosts.R;
import com.example.mycosts.db.entities.Category;

import java.util.List;

public class AllCategoriesFragment extends Fragment implements AllCategoriesContract {

    private RecyclerView recyclerView;
    private FloatingActionButton addCategory;
    private AllCategoriesPresenter presenter;
    private AllCategoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_categories, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        addCategory = view.findViewById(R.id.addCategory);

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCategory(v);
            }

        });

        AllCategoriesModel allCategoriesModel = new AllCategoriesModel();

        presenter = new AllCategoriesPresenter(allCategoriesModel);
        presenter.attachView(this);
        presenter.viewIsReady();
        return view;
    }

    @Override
    public void addNewCategory(View v) {
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
                        presenter.addCategory(category);
                    }
                    dialog.cancel();
                }
            });

            dialog.show();
        }
    }

    @Override
    public void setAdapter(List<Category> categories) {
        if (getActivity() != null) {
            adapter = new AllCategoryAdapter(getContext(), presenter, categories);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void notifyItemRemoved(int position) {
        if (adapter != null) {
            adapter.notifyItemRemoved(position);
        }
    }

    @Override
    public void notifyItemChanged(int position) {
        if (adapter != null) {
            adapter.notifyItemChanged(position);
        }
    }

    @Override
    public void changeData(List<Category> categories) {
        adapter.setCategories(categories);
    }
}
