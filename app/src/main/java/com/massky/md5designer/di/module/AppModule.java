package com.massky.md5designer.di.module;


import android.content.Context;

import com.massky.data.api.HttpHelper;
import com.massky.md5designer.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class AppModule {
    private  final App application;
    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext () {
        return  application;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(HttpHelper httpHelper) {
        return httpHelper.getOkHttpClient();
    }
}