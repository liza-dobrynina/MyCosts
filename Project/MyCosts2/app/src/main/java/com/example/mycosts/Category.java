package com.example.mycosts;

import java.util.ArrayList;

public class Category {
    private  Integer id;
    private String name;
    private Integer maxSum;


    public Category(String name, int maxSum) {
        this.name = name;
        this.maxSum = maxSum;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}



