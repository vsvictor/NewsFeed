package com.newsfeed.api;

import android.util.Log;

import retrofit.Callback;
import retrofit.Response;

public abstract class RetrofitCallback<T> implements Callback<T> {
    private static final String TAG = "RetrofitCallback";
    @Override
    public void onResponse(Response<T> response) {
        Log.i(TAG, "Success");
    }

    @Override
    public void onFailure(Throwable t) {
        Log.i(TAG, "Fail");
    }
}
