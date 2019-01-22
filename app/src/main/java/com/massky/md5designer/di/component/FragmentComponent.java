package com.massky.md5designer.di.component;
import com.massky.md5designer.di.module.FragmentModule;
import com.massky.md5designer.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class,modules = {
        FragmentModule.class
})
public interface FragmentComponent {

}
