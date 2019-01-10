package com.massky.md5designer.presenter.contract;

import com.massky.md5designer.base.IPresenter;
import com.massky.md5designer.base.IView;

public interface SplashContract {

    interface  View extends IView {
        void enterHome();
    }

    interface Presenter extends IPresenter<View> {
        void enterHome();
    }
}
