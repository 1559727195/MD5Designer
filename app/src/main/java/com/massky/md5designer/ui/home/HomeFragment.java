package com.massky.md5designer.ui.home;

import com.massky.md5designer.R;
import com.massky.md5designer.base.BaseFragment;
import com.massky.md5designer.presenter.NewHomePresenter;
import com.massky.md5designer.presenter.contract.NewHomeContract;

public class HomeFragment extends BaseFragment<NewHomePresenter> implements NewHomeContract.View {

    @Override
    protected int getContentResId() {
        return R.layout.fragment_home;
    }
}
