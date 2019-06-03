package com.example.mycosts;

import android.app.Application;

import com.example.mycosts.api.ApiClient;
import com.example.mycosts.api.ApiClientImpl;

public class App extends Application {

    public static App instance;
    private ApiClient apiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        apiClient = new ApiClientImpl(this);
    }

    public static App getInstance() {
        return instance;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }
}
