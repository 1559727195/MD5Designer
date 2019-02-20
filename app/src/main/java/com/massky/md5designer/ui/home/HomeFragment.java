package com.massky.md5designer.ui.home;

import android.view.View;

import com.crazysunj.cardslideview.CardViewPager;
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



    @Inject
    HomeAdapter mAdapter;

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
    protected void initView() {
        mHomeList.setLayoutManager(new LinearLayoutManager(mActivity));
        mHomeList.setAdapter(mAdapter);
    }

    @Override
    public void showZhihu(ZhihuNewsEntity zhihuNewsEntity) {

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
    protected void initData() {
        mPresenter.getZhihuNewsList();
    }

    @Override
    protected void initInject() {
        getFragmentComponent(new EntityModule())
                .inject(this);
    }
}
