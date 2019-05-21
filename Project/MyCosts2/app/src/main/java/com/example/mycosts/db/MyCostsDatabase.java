package com.example.mycosts.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.mycosts.db.dao.CategoryDAO;
import com.example.mycosts.db.dao.ExpenseDAO;
import com.example.mycosts.db.entities.Category;
import com.example.mycosts.db.entities.Expense;

@Database(entities = {Category.class, Expense.class}, version = 1)
public abstract class MyCostsDatabase extends RoomDatabase {
    public abstract CategoryDAO categoryDAO();

    public abstract ExpenseDAO expenseDAO();
}