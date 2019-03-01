package com.massky.md5designer.di.module;


import android.content.Context;

import com.massky.data.api.HttpHelper;
import com.massky.data.repository.gankio.GankioDataRepository;
import com.massky.data.repository.zhihu.ZhihuDataRepository;
import com.massky.domain.interactor.gankio.GankioRepository;
import com.massky.domain.repository.ZhihuRepository;
import com.massky.md5designer.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(HttpHelper httpHelper) {
        return httpHelper.getOkHttpClient();
    }

    @Provides
    @Singleton
    ZhihuRepository provideZhihuRepository(ZhihuDataRepository zhihuRepository) {
        return zhihuRepository;
    }

    @Provides
    @Singleton
    GankioRepository provideGankioRepository (GankioDataRepository gankioDataRepository) {
        return gankioDataRepository;
    }
}
