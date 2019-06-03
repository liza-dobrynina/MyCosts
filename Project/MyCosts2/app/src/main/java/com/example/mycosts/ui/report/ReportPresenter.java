package com.example.mycosts.ui.report;

import com.example.mycosts.App;
import com.example.mycosts.api.ApiClient;
import com.example.mycosts.api.OnResponseCallback;
import com.example.mycosts.api.model.Category;

import java.util.List;

public class ReportPresenter {

    private final ApiClient apiClient = App.getInstance().getApiClient();
    private final ReportContract view;

    public ReportPresenter(ReportContract view) {
        this.view = view;
    }

    public void prepareView() {
        loadData();
    }

    public void loadData() {
        apiClient.fetchAllCategories(new OnResponseCallback<List<Category>>() {
            @Override
            public void onResponse(List<Category> categories) {
                view.setData(categories);
            }
        });
    }
}
