package com.massky.md5designer.di.component;


import android.app.Activity;
import com.massky.md5designer.di.module.ActivityModule;
import com.massky.md5designer.di.scope.ActivityScope;
import com.massky.md5designer.ui.splash.SplashActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class,modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();
    void inject(SplashActivity splashActivity);
}
