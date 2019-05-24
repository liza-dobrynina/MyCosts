package com.example.mycosts.ui.expenses;

import android.view.View;
import android.widget.Spinner;

import com.example.mycosts.db.entities.Category;
import com.example.mycosts.db.entities.CategorySum;
import com.example.mycosts.db.entities.ExpenseWithCategory;

import java.util.List;

public interface AllExpenseContract {
    void setAdapter(List<ExpenseWithCategory> expenses);

    void addNewExpenseClick(View v);

    void fillCategories(Spinner spinner, List<Category> categories);

    void setSelectedCategory(Spinner spinner, Category category);

    void notifyItemRemoved(int position);

    void notifyItemChanged(int position);

    void notifyDataSetChanged();

    void changeData(List<ExpenseWithCategory> expenseWithCategories);

    void showWarning(CategorySum categorySum);
}
