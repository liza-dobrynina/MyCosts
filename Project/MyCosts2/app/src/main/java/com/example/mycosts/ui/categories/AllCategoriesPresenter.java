package com.example.mycosts.ui.categories;

import android.widget.Spinner;

import com.example.mycosts.db.entities.Category;

import java.util.List;

public class AllCategoriesPresenter {
    private AllCategoriesContract view;
    private AllCategoriesModel model;

    public AllCategoriesPresenter(AllCategoriesModel model) {
        this.model = model;
    }

    public void attachView(AllCategoriesContract view) {
        this.view = view;
    }

    public void viewIsReady() {
        loadData();
    }

    public void loadData() {
        model.getAllCategories(new AllCategoriesModel.GetAllCategoriesCallback() {
            @Override
            public void onLoad(List<Category> categories) {
                view.setAdapter(categories);
            }
        });
    }

    public void addCategory(Category category) {
        model.addCategory(category, new AllCategoriesModel.CompleteCallback() {
            @Override
            public void onComplete() {
                loadData();
            }
        });
    }

    public void deleteCategory(Category category, final int position) {
        model.deleteCategory(category, new AllCategoriesModel.CompleteCallback() {
            @Override
            public void onComplete() {
                model.getAllCategories(new AllCategoriesModel.GetAllCategoriesCallback() {
                    @Override
                    public void onLoad(List<Category> categories) {
                        view.changeData(categories);
                        view.notifyItemRemoved(position);
                    }
                });
            }
        });
    }

    public void updateCategory(Category category, final int position) {
        model.updateCategory(category, new AllCategoriesModel.CompleteCallback() {
            @Override
            public void onComplete() {
                model.getAllCategories(new AllCategoriesModel.GetAllCategoriesCallback() {
                    @Override
                    public void onLoad(List<Category> categories) {
                        view.changeData(categories);
                        view.notifyItemChanged(position);
                    }
                });
            }
        });
    }
}
