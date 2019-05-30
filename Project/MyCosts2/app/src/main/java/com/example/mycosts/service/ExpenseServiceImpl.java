package com.example.mycosts.service;

import com.example.mycosts.db.entities.CategorySum;
import com.example.mycosts.db.entities.Expense;
import com.example.mycosts.db.entities.ExpenseWithCategory;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    @Override
    public void insert(Expense expense) {

    }

    @Override
    public void delete(Expense expense) {

    }

    @Override
    public void update(Expense expense) {

    }

    @Override
    public List<ExpenseWithCategory> getAllExpensesWithCategory() {
        return null;
    }

    @Override
    public Integer getAllExpenseSumByCategory(Long categoryId) {
        return null;
    }

    @Override
    public CategorySum getExpenseSumByCategory(Long categoryId) {
        return null;
    }
}
