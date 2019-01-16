/*
  Copyright 2017 Sun Jian
  <p>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p>
  http://www.apache.org/licenses/LICENSE-2.0
  <p>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.massky.md5designer.ui.splash;
import com.massky.md5designer.base.BaseActivity;
import com.massky.md5designer.presenter.SplashPresenter;
import com.massky.md5designer.presenter.contract.SplashContract;

import butterknife.BindView;


/**
 * @author: sunjian
 * created on: 2018/4/16 下午6:26
 * description: https://github.com/crazysunj/CrazyDaily
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

//    @BindView(R.id.splash_lottie)
//    LottieAnimationView mSplashAnim;

    @Override
    protected void initListener() {
//        mSplashAnim.addAnimatorListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                mSplashAnim.removeAllAnimatorListeners();
//                mPresenter.enterHome();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
    }

    @Override
    protected int getContentResId() {
//        return R.layout.activity_splash;
        return 0;
    }

    @Override
    protected void initInject() {
//        getActivityComponent()
//                .inject(this);
    }

    @Override
    public void enterHome() {
//        MainActivity.start(this);
//        finish();
    }
}
