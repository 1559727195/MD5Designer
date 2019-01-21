package com.massky.md5designer.presenter.contract;

import com.massky.md5designer.base.IPresenter;
import com.massky.md5designer.base.IView;

import java.util.List;

public interface MainContract {
    interface View extends IView {
        void showMeinv(List<String> meinvList);

        void errorMeinv();
    }

    interface Presenter extends IPresenter<View> {
        void getMeinvList();
    }
}
