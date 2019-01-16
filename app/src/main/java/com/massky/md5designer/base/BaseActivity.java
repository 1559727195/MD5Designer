package com.massky.md5designer.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.massky.data.util.SnackbarUtil;
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

    @Override
    public void showError(String msg) {
        SnackbarUtil.show(this, msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * 注入
     */
    protected void initInject() {

    }

}
