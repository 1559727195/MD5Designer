package com.massky.md5designer.ui.note;

import com.massky.md5designer.R;
import com.massky.md5designer.base.BaseFragment;
import com.massky.md5designer.presenter.NewHomePresenter;
import com.massky.md5designer.presenter.NotePresenter;
import com.massky.md5designer.presenter.contract.NewHomeContract;
import com.massky.md5designer.presenter.contract.NoteContract;

public class NoteFragment extends BaseFragment<NotePresenter> implements NoteContract.View {

    @Override
    protected int getContentResId() {
        return R.layout.activity_note;
    }
}
