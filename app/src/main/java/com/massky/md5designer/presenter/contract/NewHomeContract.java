package com.massky.md5designer.presenter.contract;

import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.md5designer.base.IPresenter;
import com.massky.md5designer.base.IView;

public interface NewHomeContract {
    interface  View extends IView {
        void showZhihu(ZhihuNewsEntity zhihuNewsEntity);
        void switchBanner();

    }

    interface Presenter extends IPresenter<View> {
        void getZhihuNewsList();
        void getGankioList(String type);
        void startBanner();
        void endBanner();
    }
}
