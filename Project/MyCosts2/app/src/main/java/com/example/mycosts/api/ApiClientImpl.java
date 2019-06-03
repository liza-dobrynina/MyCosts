package com.example.mycosts.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mycosts.api.model.Category;
import com.example.mycosts.api.model.Expense;
import com.example.mycosts.api.model.ExpenseWithCategory;
import com.example.mycosts.api.model.FetchAllCategoriesResponse;
import com.example.mycosts.api.model.FetchAllExpensesResponse;
import com.example.mycosts.api.request.JacksonGetRequest;
import com.example.mycosts.api.request.JacksonPostRequest;
import com.example.mycosts.api.request.JacksonPutRequest;

import java.util.List;

public class ApiClientImpl implements ApiClient {

    private static final String urlBase = "http://157.230.20.13:5000/";

    private final RequestQueue requestQueue;

    public ApiClientImpl(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void fetchAllCategories(final OnResponseCallback<List<Category>> onResponseCallback) {
        JacksonGetRequest request =
                new JacksonGetRequest<>(
                        urlBase + "categories",
                        FetchAllCategoriesResponse.class,
                        new Response.Listener<FetchAllCategoriesResponse>() {
                            @Override
                            public void onResponse(FetchAllCategoriesResponse response) {
                                onResponseCallback.onResponse(response.getCategories());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace(System.err);
                            }
                        });

        requestQueue.add(request);
    }

    @Override
    public void fetchCategory(Long categoryId, final OnResponseCallback<Category> onResponseCallback) {
        JacksonGetRequest request =
                new JacksonGetRequest<>(
                        urlBase + "categories/" + categoryId,
                        Category.class,
                        new Response.Listener<Category>() {
                            @Override
                            public void onResponse(Category response) {
                                onResponseCallback.onResponse(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace(System.err);
                            }
                        });

        requestQueue.add(request);
    }

    @Override
    public void addCategory(Category category, final OnResponseCallback<Category> onResponseCallback) {
        JacksonPostRequest request =
                new JacksonPostRequest<>(
                        urlBase + "categories",
                        Category.class,
                        category,
                        new Response.Listener<Category>() {
                            @Override
                            public void onResponse(Category response) {
                                onResponseCallback.onResponse(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace(System.err);
                            }
                        });

        requestQueue.add(request);
    }

    @Override
    public void changeCategory(Category category, final OnResponseCallback<Category> onResponseCallback) {
        JacksonPutRequest request =
                new JacksonPutRequest<>(
                        urlBase + "categories/" + category.getId(),
                        Category.class,
                        category,
                        new Response.Listener<Category>() {
                            @Override
                            public void onResponse(Category response) {
                                onResponseCallback.onResponse(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace(System.err);
                            }
                        });

        requestQueue.add(request);
    }

    @Override
    public void deleteCategory(Category category, final OnResponseVoidCallback onResponseCallback) {
        StringRequest request =
                new StringRequest(
                        Request.Method.DELETE,
                        urlBase + "categories/" + category.getId(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String emptyResponse) {
                                onResponseCallback.onResponse();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace(System.err);
                            }
                        });

        requestQueue.add(request);
    }

    @Override
    public void fetchAllExpenses(final OnResponseCallback<List<ExpenseWithCategory>> onResponseCallback) {
        JacksonGetRequest request =
                new JacksonGetRequest<>(
                        urlBase + "expenses",
                        FetchAllExpensesResponse.class,
                        new Response.Listener<FetchAllExpensesResponse>() {
                            @Override
                            public void onResponse(FetchAllExpensesResponse response) {
                                onResponseCallback.onResponse(response.getExpenses());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace(System.err);
                            }
                        });

        requestQueue.add(request);
    }

    @Override
    public void addExpense(Expense expense, final OnResponseCallback<Expense> onResponseCallback) {
        JacksonPostRequest request =
                new JacksonPostRequest<>(
                        urlBase + "expenses",
                        Expense.class,
                        expense,
                        new Response.Listener<Expense>() {
                            @Override
                            public void onResponse(Expense response) {
                                onResponseCallback.onResponse(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace(System.err);
                            }
                        });

        requestQueue.add(request);
    }

    @Override
    public void changeExpense(Expense expense, final OnResponseCallback<Expense> onResponseCallback) {
        JacksonPutRequest request =
                new JacksonPutRequest<>(
                        urlBase + "expenses/" + expense.getId(),
                        Expense.class,
                        expense,
                        new Response.Listener<Expense>() {
                            @Override
                            public void onResponse(Expense response) {
                                onResponseCallback.onResponse(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace(System.err);
                            }
                        });

        requestQueue.add(request);
    }

    @Override
    public void deleteExpense(ExpenseWithCategory expenseWithCategory, final OnResponseVoidCallback onResponseCallback) {
        StringRequest request =
                new StringRequest(
                        Request.Method.DELETE,
                        urlBase + "expenses/" + expenseWithCategory.getId(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String emptyResponse) {
                                onResponseCallback.onResponse();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace(System.err);
                            }
                        });

        requestQueue.add(request);
    }
}
