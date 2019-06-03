package com.example.mycosts.ui.report;

import com.example.mycosts.api.model.Category;

import java.util.List;

public interface ReportContract {

    void setData(List<Category> categories);
}
