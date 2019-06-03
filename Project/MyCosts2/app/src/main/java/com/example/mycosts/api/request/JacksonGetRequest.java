package com.example.mycosts.api.request;

import com.android.volley.Response;

public class JacksonGetRequest<T> extends JacksonRequest<T> {

    public JacksonGetRequest(String url, Class<T> clazz,
                             Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, clazz, listener, errorListener);
    }
}
