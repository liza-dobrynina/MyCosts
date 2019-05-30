package com.example.mycosts.service;

import com.example.mycosts.db.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    void insert(Category category);

    void delete(Category category);

    void update(Category category);
}
