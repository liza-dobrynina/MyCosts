package com.example.mycosts.api;

import com.example.mycosts.api.model.Category;
import com.example.mycosts.api.model.Expense;
import com.example.mycosts.api.model.ExpenseWithCategory;

import java.util.List;

public interface ApiClient {

    void fetchAllCategories(OnResponseCallback<List<Category>> onResponseCallback);

    void fetchCategory(Long categoryId, OnResponseCallback<Category> onResponseCallback);

    void addCategory(Category category, OnResponseCallback<Category> onResponseCallback);

    void changeCategory(Category category, OnResponseCallback<Category> onResponseCallback);

    void deleteCategory(Category category, OnResponseVoidCallback onResponseCallback);

    void fetchAllExpenses(OnResponseCallback<List<ExpenseWithCategory>> onResponseCallback);

    void addExpense(Expense expense, OnResponseCallback<Expense> onResponseCallback);

    void changeExpense(Expense expense, OnResponseCallback<Expense> onResponseCallback);

    void deleteExpense(ExpenseWithCategory expenseWithCategory, OnResponseVoidCallback onResponseCallback);
}
