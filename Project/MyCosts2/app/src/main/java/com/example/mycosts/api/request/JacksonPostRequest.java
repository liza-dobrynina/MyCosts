package com.example.mycosts.api.request;

import com.android.volley.Response;

public class JacksonPostRequest<T> extends JacksonRequestWithBody<T> {

    public JacksonPostRequest(String url,
                              Class<T> clazz,
                              T data,
                              Response.Listener<T> listener,
                              Response.ErrorListener errorListener) {
        super(Method.POST, url, clazz, data, listener, errorListener);
    }
}
