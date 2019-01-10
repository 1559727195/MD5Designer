package com.massky.md5designer.base;

public interface IPresenter<T extends IView> {
    void attachView(T view);
    void detachView();
}
