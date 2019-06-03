package com.example.mycosts.api.request;

import com.android.volley.Response;

public class JacksonPutRequest<T> extends JacksonRequestWithBody<T> {

    public JacksonPutRequest(String url,
                             Class<T> clazz,
                             T data,
                             Response.Listener<T> listener,
                             Response.ErrorListener errorListener) {
        super(Method.PUT, url, clazz, data, listener, errorListener);
    }
}
