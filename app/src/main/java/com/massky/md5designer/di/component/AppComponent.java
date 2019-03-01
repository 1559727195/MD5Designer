package com.massky.md5designer.di.component;


import android.content.Context;

import com.massky.domain.interactor.gankio.GankioRepository;
import com.massky.domain.repository.ZhihuRepository;
import com.massky.md5designer.di.module.AppModule;
import javax.inject.Singleton;
import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context provideContext();
    OkHttpClient provideOkhttpClient();
    ZhihuRepository provideZhihuRepository();
    GankioRepository provideGankioRepository();
}
