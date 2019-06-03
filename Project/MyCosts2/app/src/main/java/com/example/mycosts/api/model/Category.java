package com.example.mycosts.api.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {

    private Long id;
    private String name;
    private Integer maxSum;
    private Integer currentSum;

    private Category() {}

    public Category(String name, Integer maxSum) {
        this.name = name;
        this.maxSum = maxSum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(Integer maxSum) {
        this.maxSum = maxSum;
    }

    public Integer getCurrentSum() {
        return currentSum;
    }

    public void setCurrentSum(Integer currentSum) {
        this.currentSum = currentSum;
    }
}
