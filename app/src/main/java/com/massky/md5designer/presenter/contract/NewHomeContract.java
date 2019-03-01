package com.massky.md5designer.presenter.contract;

import com.massky.domain.entity.gankio.GankioEntity;
import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.md5designer.base.IPresenter;
import com.massky.md5designer.base.IView;

import java.util.List;

public interface NewHomeContract {
    interface  View extends IView {
        void showZhihu(ZhihuNewsEntity zhihuNewsEntity);
        void switchBanner();
        void showGankio (List<GankioEntity.ResultsEntity> gankioList);

    }

    interface Presenter extends IPresenter<View> {
        void getZhihuNewsList();
        void getGankioList(String type);
        void startBanner();
        void endBanner();
    }
}
