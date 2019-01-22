package com.massky.md5designer.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.massky.data.util.SnackbarUtil;
import com.massky.md5designer.R;
import com.massky.md5designer.app.App;
import com.massky.md5designer.di.component.DaggerFragmentComponent;
import com.massky.md5designer.di.component.FragmentComponent;
import com.massky.md5designer.di.module.EntityModule;
import com.massky.md5designer.di.module.FragmentModule;
import com.massky.md5designer.util.DeviceUtil;

import javax.inject.Inject;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment <T extends IPresenter> extends Fragment implements  IView{
    @Inject
    protected  T mPresenter;
    private Unbinder mUnBinder;
    protected AppCompatActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = (AppCompatActivity) getActivity();
        onPrepare();
        View rootView = inflater.inflate(getContentResId(), container, false);
        mUnBinder = ButterKnife.bind(this, rootView);
        initView();
        initListener();
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    @Override
    public void showError(String msg) {
        SnackbarUtil.show(mActivity, msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

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

    protected int getColor(@ColorRes int color) {
        return ContextCompat.getColor(mActivity, color);
    }

    protected void setSupportActionBar(Toolbar toolbar) {
        mActivity.setSupportActionBar(toolbar);
    }

    protected void finish() {
        mActivity.finish();
    }

    protected void showBack() {
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_back);
        }
    }

    protected void showBack(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(v -> mActivity.finish());
    }

    /**
     * 添加一个假的statusbar
     *
     * @param color 颜色
     */
    protected void addStatusBar(LinearLayout linearLayout, @ColorInt int color) {
        View statusBarView = new View(mActivity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DeviceUtil.getStatusBarHeight(mActivity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(color);
        linearLayout.addView(statusBarView, 0);
    }

    /**
     * 返回布局资源id
     *
     * @return int
     */
    protected abstract int getContentResId();

    /**
     * 注入
     */
    protected void initInject() {

    }

    protected FragmentComponent getFragmentComponent(EntityModule entityModule) {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getInstance().getAppComponent())
                .fragmentModule(getFragmentModule())
                .entityModule(entityModule)
                .build();
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getInstance().getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    private FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

}
