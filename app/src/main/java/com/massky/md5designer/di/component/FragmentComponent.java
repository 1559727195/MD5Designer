package com.massky.md5designer.di.component;
import com.massky.md5designer.di.module.EntityModule;
import com.massky.md5designer.di.module.FragmentModule;
import com.massky.md5designer.di.scope.FragmentScope;
import com.massky.md5designer.ui.home.HomeFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,modules = {
        FragmentModule.class,EntityModule.class
})
public interface FragmentComponent {
        void inject(HomeFragment homeFragment);
}
