package com.example.mycosts.ui.categories;

import com.example.mycosts.App;
import com.example.mycosts.api.ApiClient;
import com.example.mycosts.api.OnResponseCallback;
import com.example.mycosts.api.OnResponseVoidCallback;
import com.example.mycosts.api.model.Category;

import java.util.Collections;
import java.util.List;

public class CategoriesPresenter {

    private final ApiClient apiClient = App.getInstance().getApiClient();
    private CategoriesContract view;

    public void attachView(CategoriesContract view) {
        this.view = view;
    }

    public void prepareView() {
        view.setAdapter(Collections.<Category>emptyList());
        loadData();
    }

    public void loadData() {
        apiClient.fetchAllCategories(new OnResponseCallback<List<Category>>() {
            @Override
            public void onResponse(List<Category> categories) {
                view.setAdapter(categories);
            }
        });
    }

    public void addCategory(Category category) {
        apiClient.addCategory(category, new OnResponseCallback<Category>() {
            @Override
            public void onResponse(Category newCategory) {
                loadData();
            }
        });
    }

    public void deleteCategory(Category category, final int position) {
        apiClient.deleteCategory(category, new OnResponseVoidCallback() {
            @Override
            public void onResponse() {
                apiClient.fetchAllCategories(new OnResponseCallback<List<Category>>() {
                    @Override
                    public void onResponse(List<Category> categories) {
                        view.changeData(categories);
                        view.notifyItemRemoved(position);
                    }
                });
            }
        });
    }

    public void updateCategory(Category category, final int position) {
        apiClient.changeCategory(category, new OnResponseCallback<Category>() {
            @Override
            public void onResponse(Category changedCategory) {
                apiClient.fetchAllCategories(new OnResponseCallback<List<Category>>() {
                    @Override
                    public void onResponse(List<Category> categories) {
                        view.changeData(categories);
                        view.notifyItemChanged(position);
                    }
                });
            }
        });
    }
}
