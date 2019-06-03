package com.example.mycosts.api.model;

import java.util.List;

public class FetchAllExpensesResponse {

    private List<ExpenseWithCategory> expenses;

    public List<ExpenseWithCategory> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseWithCategory> expenses) {
        this.expenses = expenses;
    }
}
