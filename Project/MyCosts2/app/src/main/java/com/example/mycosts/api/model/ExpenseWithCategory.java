package com.example.mycosts.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ExpenseWithCategory {

    private Long id;
    private String name;
    private Date date;
    @JsonProperty("summ")
    private Integer sum;
    private Category category;

    private ExpenseWithCategory() {}

    public ExpenseWithCategory(String name, Date date, Integer sum, Category category) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

