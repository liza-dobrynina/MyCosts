package com.example.mycosts;

import android.icu.util.LocaleData;


public class Expense {

    private Integer id;
    private String date;
    private Integer sum;
    private Category category;

    public Expense(String name, String date, Integer sum, Category category){

        this.date = date;
        this.sum = sum;
        this.category = category;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getSum(){
        return sum;
    }

    public Category getCategory(){
        return category;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
}

