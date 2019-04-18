package com.example.mycosts;
import android.icu.util.LocaleData;

import java.util.ArrayList;

public class System {

    public void addExpense(ArrayList<Expense> expenses, Category category, String name, String date, Integer sum){
        expenses.add(new Expense(name, date, sum, category));
    }
}
