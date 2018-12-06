package com.massky.md5designer.app;

import android.app.Application;
import com.bumptech.glide.request.target.ViewTarget;
import com.massky.data.util.LoggerUtil;
import com.massky.md5designer.BuildConfig;
import com.massky.md5designer.R;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_tag);
        LoggerUtil.init(BuildConfig.DEBUG);
        initWeex();


    }

    private void initWeex() {
        InitConfig config = new InitConfig.Builder()
                .setImgAdapter(null)
                .setHttpAdapter(null)
                .build();

        WXSDKEngine.initialize(this, config);
        try {
            WXSDKEngine.registerComponent("tabPager", WXTabPagerComponent.class);
        } catch (WXException e) {
            LoggerUtil.d(e.getMessage());
        }
    }
}
