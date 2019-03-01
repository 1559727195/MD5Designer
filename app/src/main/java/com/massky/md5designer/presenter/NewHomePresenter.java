package com.massky.md5designer.presenter;

import com.massky.domain.entity.gankio.GankioEntity;
import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.domain.interactor.gankio.GankioUseCase;
import com.massky.domain.interactor.zhihu.ZhihuNewsListUseCase;
import com.massky.md5designer.base.BasePresenter;
import com.massky.md5designer.base.BaseSubscriber;
import com.massky.md5designer.presenter.contract.NewHomeContract;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class NewHomePresenter extends BasePresenter<NewHomeContract.View> implements NewHomeContract.Presenter {

    private ZhihuNewsListUseCase mZhihuUseCase;
    private Disposable mBannerDisposable;
    private GankioUseCase mGankioUseCase;

    @Inject
    NewHomePresenter(ZhihuNewsListUseCase zhihuUseCase, GankioUseCase gankioUseCase) {
        mZhihuUseCase = zhihuUseCase;
        mGankioUseCase = gankioUseCase;
    }


    @Override
    public void getZhihuNewsList() {
        mZhihuUseCase.execute(new BaseSubscriber<ZhihuNewsEntity>() {
            @Override
            public void onNext(ZhihuNewsEntity zhihuNewsEntity) {
                 mView.showZhihu(zhihuNewsEntity);
            }
        });
    }

    @Override
    public void getGankioList(String type) {
        mGankioUseCase.execute(GankioUseCase.Params.get(type, 10), new
                BaseSubscriber<List<GankioEntity.ResultsEntity>>() {
                    @Override
                    public void onNext(List<GankioEntity.ResultsEntity> resultsEntities) {
                        mView.showGankio(resultsEntities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.showError(e.getMessage());
                    }
                });
    }

    @Override
    public void startBanner() {
        if (mBannerDisposable == null) {
            mBannerDisposable = Flowable.interval(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                    .subscribeWith(new BaseSubscriber<Long>() {
                        @Override
                        public void onNext(Long aLong) {
                            mView.switchBanner();
                        }
                    });
        }
    }

    @Override
    public void endBanner() {

    }
}
