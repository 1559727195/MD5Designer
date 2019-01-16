package com.massky.md5designer.presenter;

import android.widget.BaseAdapter;

import com.massky.domain.interactor.splash.SplashUseCase;
import com.massky.md5designer.base.BasePresenter;
import com.massky.md5designer.base.BaseSubscriber;
import com.massky.md5designer.di.scope.ActivityScope;
import com.massky.md5designer.presenter.contract.SplashContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.View> implements
SplashContract.Presenter{

    private SplashUseCase mSplashUseCase;
    @Inject
    SplashPresenter (SplashUseCase splashUseCase) {
        mSplashUseCase = splashUseCase;
    }


    @Override
    public void enterHome() {
        mSplashUseCase.execute(SplashUseCase.Params.get(1, TimeUnit.SECONDS)
                , new BaseSubscriber<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        mView.enterHome();
                    }
                });
    }

    @Override
    public void detachView() {
        super.detachView();
        mSplashUseCase.dispose();

    }
}
