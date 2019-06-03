package com.example.mycosts.api.model;

import java.util.List;

public class FetchAllCategoriesResponse {

    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    private void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
