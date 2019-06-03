package com.example.mycosts.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Expense {

    private Long id;
    private String name;
    private Date date;
    @JsonProperty("summ")
    private Integer sum;
    private Long categoryId;

    private Expense() {}

    public Expense(String name, Date date, Integer sum, Long categoryId) {
        this(null, name, date, sum, categoryId);
    }

    public Expense(Long id, String name, Date date, Integer sum, Long categoryId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.sum = sum;
        this.categoryId = categoryId;
    }

    public static Expense fromExpenseWithCategory(ExpenseWithCategory expenseWithCategory) {
        return new Expense(
                expenseWithCategory.getId(),
                expenseWithCategory.getName(),
                expenseWithCategory.getDate(),
                expenseWithCategory.getSum(),
                expenseWithCategory.getCategory().getId());
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

