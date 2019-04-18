package com.example.mycosts;

import java.util.ArrayList;

public class Category {

    private String name;
    private Integer maxSum;
    private Integer nowSum;

    public Category(String name, int maxSum) {
        this.name = name;
        this.maxSum = maxSum;
        this.nowSum = null;
    }

    public String getName() {
        return this.name;
    }

    public Integer getMaxSum() {
        return maxSum;
    }

    public Integer getNowSum() {
        return nowSum;
    }

    public void setNowSum(Integer value) {
        this.nowSum = nowSum + value;
    }

    public  void changeCategory(String newName, Integer newSum){
        this.name = newName;
        this.maxSum = newSum;
    }
}


