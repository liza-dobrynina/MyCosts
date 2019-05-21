package com.example.mycosts.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.mycosts.db.converters.DateConverter;

import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id", onDelete = ForeignKey.CASCADE))
public class Expense {

    @PrimaryKey
    private Long id;
    private String name;
    @TypeConverters({DateConverter.class})
    private Date date;
    private Integer sum;
    @ColumnInfo(name = "category_id")
    private Long categoryId;

    public Expense(String name, Date date, Integer sum, Long categoryId) {
        this.name = name;
        this.date = date;
        this.sum = sum;
        this.categoryId = categoryId;
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

