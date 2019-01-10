package com.massky.md5designer.di.component;


import com.massky.md5designer.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    OkHttpClient provideOkhttpClient();

}
