package com.example.mycosts.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mycosts.db.entities.Expense;
import com.example.mycosts.db.entities.ExpenseWithCategory;

import java.util.List;

@Dao
public interface ExpenseDAO {

    @Insert
    void insert(Expense expense);

    @Delete
    void delete(Expense expense);

    @Update
    void update(Expense expense);

    @Query("SELECT c.id as 'category_id', c.name as 'category_name', c.max_sum as 'category_max_sum', e.id as 'expense_id', " +
            "e.name as 'expense_name', e.sum as 'expense_sum', e.date as 'expense_date', e.category_id as 'expense_category_id' " +
            "FROM category c inner join expense e on e.category_id = c.id order by e.date desc")
    List<ExpenseWithCategory> getAllExpensesWithCategory();
}
