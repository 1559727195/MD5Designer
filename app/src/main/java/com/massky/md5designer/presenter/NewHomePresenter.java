package com.massky.md5designer.presenter;

import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.domain.interactor.zhihu.ZhihuNewsListUseCase;
import com.massky.md5designer.base.BasePresenter;
import com.massky.md5designer.base.BaseSubscriber;
import com.massky.md5designer.presenter.contract.NewHomeContract;
import javax.inject.Inject;

public class NewHomePresenter extends BasePresenter<NewHomeContract.View> implements NewHomeContract.Presenter {

    private ZhihuNewsListUseCase mZhihuUseCase;
    @Inject
    NewHomePresenter(ZhihuNewsListUseCase zhihuUseCase) {
        mZhihuUseCase = zhihuUseCase;
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
}
