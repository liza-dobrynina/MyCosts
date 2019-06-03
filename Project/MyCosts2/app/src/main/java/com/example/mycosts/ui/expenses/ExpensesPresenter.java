package com.example.mycosts.ui.expenses;

import android.widget.Spinner;

import com.example.mycosts.App;
import com.example.mycosts.api.ApiClient;
import com.example.mycosts.api.OnResponseCallback;
import com.example.mycosts.api.OnResponseVoidCallback;
import com.example.mycosts.api.model.Category;
import com.example.mycosts.api.model.Expense;
import com.example.mycosts.api.model.ExpenseWithCategory;

import java.util.Collections;
import java.util.List;

public class ExpensesPresenter {

    private final ApiClient apiClient = App.getInstance().getApiClient();
    private ExpensesContract view;

    public void attachView(ExpensesContract view) {
        this.view = view;
    }

    public void prepareView() {
        view.setAdapter(Collections.<ExpenseWithCategory>emptyList());
        loadData();
    }

    public void loadData() {
        apiClient.fetchAllExpenses(new OnResponseCallback<List<ExpenseWithCategory>>() {
            @Override
            public void onResponse(List<ExpenseWithCategory> expenseWithCategoryList) {
                view.setAdapter(expenseWithCategoryList);
            }
        });
    }

    public void addExpense(final Expense expense) {
        apiClient.addExpense(expense, new OnResponseCallback<Expense>() {
            @Override
            public void onResponse(Expense newExpense) {
                loadData();
                checkExpenseMaxSum(newExpense);
            }
        });
    }

    private void checkExpenseMaxSum(Expense expense) {
        apiClient.fetchCategory(expense.getCategoryId(), new OnResponseCallback<Category>() {
            @Override
            public void onResponse(Category category) {
                if (category.getCurrentSum() > category.getMaxSum()) {
                    view.showWarning(category);
                }
            }
        });
    }

    public void fillCategories(final Spinner spinner) {
        apiClient.fetchAllCategories(new OnResponseCallback<List<Category>>() {
            @Override
            public void onResponse(List<Category> categories) {
                view.fillCategories(spinner, categories);
            }
        });
    }

    public void fillAndSetSelected(final Spinner spinner, final Category category) {
        apiClient.fetchAllCategories(new OnResponseCallback<List<Category>>() {
            @Override
            public void onResponse(List<Category> categories) {
                view.fillCategories(spinner, categories);
                view.setSelectedCategory(spinner, category);
            }
        });
    }

    public void updateExpense(final Expense expense, final int position, final boolean updateAll) {
        apiClient.changeExpense(expense, new OnResponseCallback<Expense>() {
            @Override
            public void onResponse(Expense changedExpense) {
                apiClient.fetchAllExpenses(new OnResponseCallback<List<ExpenseWithCategory>>() {
                    @Override
                    public void onResponse(List<ExpenseWithCategory> expenseWithCategoryList) {
                        view.changeData(expenseWithCategoryList);
                        if (updateAll) {
                            view.notifyDataSetChanged();
                        } else {
                            view.notifyItemChanged(position);
                        }
                        checkExpenseMaxSum(expense);
                    }
                });
            }
        });
    }

    public void deleteExpense(final ExpenseWithCategory expenseWithCategory, final int position) {
        apiClient.deleteExpense(expenseWithCategory, new OnResponseVoidCallback() {
            @Override
            public void onResponse() {
                apiClient.fetchAllExpenses(new OnResponseCallback<List<ExpenseWithCategory>>() {
                    @Override
                    public void onResponse(List<ExpenseWithCategory> expenseWithCategoryList) {
                        view.changeData(expenseWithCategoryList);
                        view.notifyItemRemoved(position);
                    }
                });
            }
        });
    }
}
