package com.example.mycosts.api.request;

import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.example.mycosts.App;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableSet;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class JacksonRequestWithBody<T> extends JacksonRequest<T> {

    private static final ImmutableSet<Integer> VALID_METHODS = ImmutableSet.of(Method.POST, Method.PUT);

    private final T data;

    JacksonRequestWithBody(int method,
                           String url,
                           Class<T> clazz,
                           T data,
                           Response.Listener<T> listener,
                           Response.ErrorListener errorListener) {
        super(method, url, clazz, listener, errorListener);
        checkArgument(VALID_METHODS.contains(method));
        this.data = data;
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public byte[] getBody() {
        try {
            return data == null ? null : getObjectMapper().writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            VolleyLog.wtf("Couldn't write request data as bytes array");
            return null;
        }
    }
}
