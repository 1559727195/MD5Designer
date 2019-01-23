package com.massky.md5designer.ui.contact;

import com.massky.md5designer.R;
import com.massky.md5designer.base.BaseFragment;
import com.massky.md5designer.presenter.ContactPresenter;
import com.massky.md5designer.presenter.NewHomePresenter;
import com.massky.md5designer.presenter.contract.ContactContract;
import com.massky.md5designer.presenter.contract.NewHomeContract;

public class ContactFragment extends BaseFragment<ContactPresenter> implements ContactContract.View {

    @Override
    protected int getContentResId() {
        return R.layout.activity_contact;
    }
}
