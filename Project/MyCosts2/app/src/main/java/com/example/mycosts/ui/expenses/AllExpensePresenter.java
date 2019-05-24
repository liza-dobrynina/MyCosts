package com.example.mycosts.ui.expenses;

import android.widget.Spinner;

import com.example.mycosts.db.entities.Category;
import com.example.mycosts.db.entities.CategorySum;
import com.example.mycosts.db.entities.Expense;
import com.example.mycosts.db.entities.ExpenseWithCategory;
import com.example.mycosts.ui.categories.AllCategoriesModel;

import java.util.List;

public class AllExpensePresenter {
    private AllExpenseContract view;
    private AllExpenseModel model;

    public AllExpensePresenter(AllExpenseModel model) {
        this.model = model;
    }

    public void attachView(AllExpenseContract view) {
        this.view = view;
    }

    public void viewIsReady() {
        loadData();
    }

    public void loadData() {
        model.getAllExpenses(new AllExpenseModel.GetAllExpensesCallback() {
            @Override
            public void onLoad(List<ExpenseWithCategory> expenses) {
                view.setAdapter(expenses);
            }
        });
    }

    public void addExpense(final Expense expense) {
        model.insertExpense(expense, new AllCategoriesModel.CompleteCallback() {
            @Override
            public void onComplete() {
                loadData();
                checkExpenseMaxSum(expense);
            }
        });
    }

    private void checkExpenseMaxSum(Expense expense) {
        model.getAllExpenseSum(expense.getCategoryId(), new AllExpenseModel.GetAllExpenseSumByCategoryCallback() {
            @Override
            public void onLoad(CategorySum categorySum) {
                if (categorySum.getActualSum() > categorySum.getMaxSum()) {
                    view.showWarning(categorySum);
                }
            }
        });
    }

    public void fillCategories(final Spinner spinner) {
        model.getAllCategories(new AllCategoriesModel.GetAllCategoriesCallback() {
            @Override
            public void onLoad(List<Category> categories) {
                view.fillCategories(spinner, categories);
            }
        });
    }

    public void fillAndSetSelected(final Spinner spinner, final Category category) {
        model.getAllCategories(new AllCategoriesModel.GetAllCategoriesCallback() {
            @Override
            public void onLoad(List<Category> categories) {
                view.fillCategories(spinner, categories);
                view.setSelectedCategory(spinner, category);
            }
        });
    }

    public void updateExpense(final Expense expense, final int position, final boolean updateAll) {
        model.updateExpense(expense, new AllCategoriesModel.CompleteCallback() {
            @Override
            public void onComplete() {
                model.getAllExpenses(new AllExpenseModel.GetAllExpensesCallback() {
                    @Override
                    public void onLoad(List<ExpenseWithCategory> expenses) {
                        view.changeData(expenses);
                        if (updateAll)
                            view.notifyDataSetChanged();
                        else
                            view.notifyItemChanged(position);
                        checkExpenseMaxSum(expense);
                    }
                });
            }
        });
    }

    public void deleteExpense(final Expense expense, final int position) {
        model.deleteExpense(expense, new AllCategoriesModel.CompleteCallback() {
            @Override
            public void onComplete() {
                model.getAllExpenses(new AllExpenseModel.GetAllExpensesCallback() {
                    @Override
                    public void onLoad(List<ExpenseWithCategory> expenses) {
                        view.changeData(expenses);
                        view.notifyItemRemoved(position);
                    }
                });
            }
        });
    }
}
