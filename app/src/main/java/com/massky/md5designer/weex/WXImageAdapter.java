package com.massky.md5designer.weex;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.widget.ImageView;

import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

public class WXImageAdapter implements IWXImgLoaderAdapter {
    @Override
    public void setImage(String url, ImageView view, WXImageQuality quality, WXImageStrategy strategy) {

        if (TextUtils.isEmpty(url)) {
            return;
        }
        final Context context = view.getContext();

        if (url.startsWith("mipmap://")) {
            String resIdStr = getResIdStr(url);
            if (TextUtils.isEmpty(resIdStr)) {
                return;
            }
           int imgId = context.getResources().getIdentifier(resIdStr,
                   "mipmap",context.getPackageName());

            view.setImageResource(imgId);

            return;
        }














    }

    private String getResIdStr(String url) {
        int start = url.lastIndexOf("/") + 1;
        int end = url.indexOf(".");
        if (start > end) {
            return null;
        }
        return url.substring(start, end);
    }

}
