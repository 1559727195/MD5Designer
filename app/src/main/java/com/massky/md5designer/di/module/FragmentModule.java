package com.massky.md5designer.di.module;
import android.app.Activity;

import com.massky.md5designer.di.scope.FragmentScope;

import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private Fragment fragment;
    public FragmentModule (Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Activity provideActivity(){
        return fragment.getActivity();
    }
}