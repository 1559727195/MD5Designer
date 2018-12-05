package com.massky.md5designer.app;

import android.app.Application;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.massky.md5designer.R;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_tag);
      
    }
}
