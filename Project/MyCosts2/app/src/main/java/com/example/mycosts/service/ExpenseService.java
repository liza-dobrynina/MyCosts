package com.example.mycosts.service;

import com.example.mycosts.db.entities.CategorySum;
import com.example.mycosts.db.entities.Expense;
import com.example.mycosts.db.entities.ExpenseWithCategory;

import java.util.List;

public interface ExpenseService {

    void insert(Expense expense);

    void delete(Expense expense);

    void update(Expense expense);

    List<ExpenseWithCategory> getAllExpensesWithCategory();

    Integer getAllExpenseSumByCategory(Long categoryId);

    CategorySum getExpenseSumByCategory(Long categoryId);
}
