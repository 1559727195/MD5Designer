package com.massky.md5designer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.massky.md5designer.app.App;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity <T extends IPresenter> extends AppCompatActivity implements IView{


    private Unbinder mUnBinder;

    @Inject
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResId());
        mUnBinder = ButterKnife.bind(this);
        App.getInstance().addActivity(this);
        onPrepare();
        initView();
        initListener();
        initData();
    }


    /**
     * @return 返回布局资源id
     */
    protected abstract int getContentResId();

    @SuppressWarnings("unchecked")
    private void onPrepare() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected void initListener() {

    }

    protected void initData() {

    }

    protected void initView() {

    }

    /**
     * 注入
     */
    protected void initInject() {

    }

}
