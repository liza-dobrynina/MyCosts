package com.example.mycosts.db.entities;

public class CategorySum {
    private Integer actualSum;
    private Integer maxSum;

    public CategorySum() {
    }

    public Integer getActualSum() {
        return actualSum;
    }

    public void setActualSum(Integer actualSum) {
        this.actualSum = actualSum;
    }

    public Integer getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(Integer maxSum) {
        this.maxSum = maxSum;
    }
}
