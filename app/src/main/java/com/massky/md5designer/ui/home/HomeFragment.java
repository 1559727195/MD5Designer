package com.massky.md5designer.ui.home;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import com.crazysunj.cardslideview.CardViewPager;
import com.google.android.material.appbar.AppBarLayout;
import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.md5designer.R;
import com.massky.md5designer.base.BaseFragment;
import com.massky.md5designer.di.module.EntityModule;
import com.massky.md5designer.presenter.NewHomePresenter;
import com.massky.md5designer.presenter.contract.NewHomeContract;
import com.massky.md5designer.ui.adapter.HomeAdapter;
import com.massky.md5designer.view.banner.BannerCardHandler;
import com.massky.md5designer.view.banner.WrapBannerView;
import java.util.List;
import javax.inject.Inject;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class HomeFragment extends BaseFragment<NewHomePresenter> implements NewHomeContract.View {
    @BindView(R.id.home_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.home_vp)
    CardViewPager mHomeBanner;
    @BindView(R.id.wrap_banner)
    WrapBannerView mWrapBanner;
    @BindView(R.id.home_list)
    RecyclerView mHomeList;
    @BindView(R.id.home_appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.home_title)
    TextView mTitle;
    @BindView(R.id.home_toolbar)
    Toolbar mToolbar;


    @Inject
    HomeAdapter mAdapter;
    private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();
    private Drawable mNavigationIcon;

    @Override
    protected int getContentResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initListener() {
        mRefresh.setOnRefreshListener(this::onRefresh);
        mAppbar.addOnOffsetChangedListener(this::handleAppbarOffsetChangedListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.startBanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.endBanner();
    }

    private void onRefresh() {
        initData();
    }

    private void handleAppbarOffsetChangedListener(AppBarLayout appBarLayout, int verticalOffset) {
        final int totalScrollRange = appBarLayout.getTotalScrollRange();
        final float percent = Math.abs(verticalOffset * 1.0f / totalScrollRange);
        mRefresh.setEnabled(verticalOffset == 0);
        final int color = getColor(R.color.colorPrimary);
//        mTitle.setTextColor((int) mArgbEvaluator.evaluate(percent, color, Color.WHITE));
        mTitle.setTextColor((Integer) mArgbEvaluator.evaluate(percent, color, Color.WHITE));
        if (mNavigationIcon != null) {
            mNavigationIcon.setColorFilter((int) mArgbEvaluator.evaluate(percent, color, Color.WHITE), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationIcon(R.mipmap.ic_scan);
        mNavigationIcon = mToolbar.getNavigationIcon();
        mHomeList.setLayoutManager(new LinearLayoutManager(mActivity));
        mHomeList.setAdapter(mAdapter);
    }

    @Override
    public void showZhihu(ZhihuNewsEntity zhihuNewsEntity) {
        stopRefresh();
        List<ZhihuNewsEntity.StoriesEntity> stories = zhihuNewsEntity.getStories();
        if (stories != null && !stories.isEmpty()) {
            mAdapter.notifyZhihuNewsList(stories);
        }


        List<ZhihuNewsEntity.TopStoriesEntity> topStories = zhihuNewsEntity.getTop_stories();
        if (topStories == null || topStories.isEmpty()) {
            mHomeBanner.setVisibility(View.GONE);
        } else {
            mHomeBanner.setVisibility(View.VISIBLE);
            mHomeBanner.bind(mActivity.getSupportFragmentManager(), new BannerCardHandler(), topStories);
        }
    }

    @Override
    public void switchBanner() {
        mHomeBanner.setCurrentItem(mHomeBanner.getCurrentItem() + 1, true);
    }


    private void stopRefresh() {
        if (mRefresh.isRefreshing()) {
            mRefresh.setRefreshing(false);
        }
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
