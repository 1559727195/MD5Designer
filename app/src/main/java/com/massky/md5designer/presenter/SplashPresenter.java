package com.massky.md5designer.presenter;

import android.widget.BaseAdapter;

import com.massky.md5designer.base.BasePresenter;
import com.massky.md5designer.di.scope.ActivityScope;
import com.massky.md5designer.presenter.contract.SplashContract;

import javax.inject.Inject;

@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.View> implements
SplashContract.Presenter{

    @Inject
    SplashPresenter () {

    }


    @Override
    public void enterHome() {

    }

    @Override
    public void detachView() {
        super.detachView();

    }
}
