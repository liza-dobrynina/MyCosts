package com.example.mycosts.ui.categories;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mycosts.R;
import com.example.mycosts.api.model.Category;

import java.util.List;

public class CategoriesFragment extends Fragment implements CategoriesContract {

    private RecyclerView recyclerView;
    private CategoriesPresenter presenter;
    private CategoriesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Категории");
        View view = inflater.inflate(R.layout.fragment_all_categories, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        view.findViewById(R.id.addCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCategory(v);
            }

        });


        presenter = new CategoriesPresenter();
        presenter.attachView(this);
        presenter.prepareView();
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
            adapter = new CategoriesAdapter(getContext(), presenter, categories);
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
