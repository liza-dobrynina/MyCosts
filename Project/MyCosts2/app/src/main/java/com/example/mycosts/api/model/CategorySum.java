package com.example.mycosts.api.model;

public class CategorySum {

    private Integer currentSum;
    private Integer maxSum;

    public CategorySum() {
    }

    public Integer getCurrentSum() {
        return currentSum;
    }

    public void setCurrentSum(Integer currentSum) {
        this.currentSum = currentSum;
    }

    public Integer getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(Integer maxSum) {
        this.maxSum = maxSum;
    }
}
