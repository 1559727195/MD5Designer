package com.massky.data.api;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;

@Singleton
public class HttpHelper {
    private OkHttpClient mOkHttpClient;
    @Inject
    public HttpHelper(Context context) {

    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

}
