package com.example.mycosts.db.entities;

import android.arch.persistence.room.Embedded;

public class ExpenseWithCategory {

    @Embedded(prefix = "expense_")
    private Expense expense;
    @Embedded(prefix = "category_")
    private Category category;

    public ExpenseWithCategory() {
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

