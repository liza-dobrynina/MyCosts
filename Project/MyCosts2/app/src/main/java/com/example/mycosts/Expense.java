package com.example.mycosts;

import android.icu.util.LocaleData;


public class Expense {
    private String name;
    private String date;
    private Integer sum;
    private Category category;

    public Expense(String name, String date, Integer sum, Category category){
        this.name = name;
        this.date = date;
        this.sum = sum;
        this.category = category;
    }

    public String getName(){
        return name;
    }

    public Integer getSum(){
        return sum;
    }

    public Category getCategory(){
        return category;
    }

    public void changeExpense(String name, String date, Integer sum, Category category){
        this.name = name;
        this.date = date;
        this.sum = sum;
        this.category = category;
    }
}

