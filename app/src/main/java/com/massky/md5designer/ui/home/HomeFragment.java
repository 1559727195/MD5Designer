package com.massky.md5designer.ui.home;

import com.crazysunj.cardslideview.CardViewPager;
import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.md5designer.R;
import com.massky.md5designer.base.BaseFragment;
import com.massky.md5designer.di.module.EntityModule;
import com.massky.md5designer.presenter.NewHomePresenter;
import com.massky.md5designer.presenter.contract.NewHomeContract;
import com.massky.md5designer.view.banner.WrapBannerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class HomeFragment extends BaseFragment<NewHomePresenter> implements NewHomeContract.View {
    @BindView(R.id.home_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.home_vp)
    CardViewPager mHomeBanner;
    @BindView(R.id.wrap_banner)
    WrapBannerView mWrapBanner;


    @Override
    protected int getContentResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initListener() {
        mRefresh.setOnRefreshListener(this::onRefresh);
    }

    private void onRefresh() {

    }

    @Override
    public void showZhihu(ZhihuNewsEntity zhihuNewsEntity) {

    }

    @Override
    protected void initData() {
        mPresenter.getZhihuNewsList();
    }

    @Override
    protected void initInject() {
        getFragmentComponent(new EntityModule())
                .inject(this);
    }
}
