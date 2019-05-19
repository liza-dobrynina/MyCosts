package com.example.mycosts;

import java.util.Date;

public class Expense {

    private String name;
    private Integer id;
    private Date date;
    private Integer sum;
    private Category category;

    public Expense(String name, Date date, Integer sum, Category category ) {
        this.id = App.getNextExpenseId();
        this.name = name;
        this.date = date;
        this.sum = sum;
        this.category = category;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getId() {
        return id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory(){
        return category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

