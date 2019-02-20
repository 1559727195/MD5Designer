package com.massky.md5designer.ui.adapter.helper;

import android.graphics.Color;
import com.crazysunj.multitypeadapter.helper.AsynAdapterHelper;
import com.massky.domain.entity.base.MultiTypeIdEntity;
import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.md5designer.R;
import java.util.List;
import javax.inject.Inject;
import androidx.recyclerview.widget.DiffUtil;

public class HomeAdapterHelper extends AsynAdapterHelper
<MultiTypeIdEntity>{

    public static final int MIN_ZHIHU = 2;
    public static final int MIN_GANK_IO = 3;

    public static final int LEVEL_ZHIHU = 0;
    public static final int LEVEL_GANK_IO = 1;
    public static final int LEVEL_WEATHER = 2;
    public static final int LEVEL_NEIHAN = 3;
    public static final int LEVEL_GAOXIAO = 4;

    @Inject
    public HomeAdapterHelper() {
        super(null);
    }

    @Override
    protected void registerModule() {
        registerModule(LEVEL_ZHIHU)
                .type(ZhihuNewsEntity.StoriesEntity.TYPE_ZHIHU_NEWS)
                .layoutResId(R.layout.item_zhihu_news)
                .headerResId(R.layout.header_common)
                .footerResId(R.layout.footer_common)
                .minSize(MIN_ZHIHU)
                .isFolded(true)
                .register();


    }

    public static int getColor(int level) {
        switch (level) {
            case LEVEL_ZHIHU:
                return Color.parseColor("#BEE7E9");
            case LEVEL_GANK_IO:
                return Color.parseColor("#19CAAD");
            case LEVEL_GAOXIAO:
                return Color.parseColor("#FF5C8D");
            default:
                return Color.parseColor("#19CAAD");
        }
    }

    @Override
    protected DiffUtil.Callback getDiffCallBack(List<MultiTypeIdEntity> oldData, List<MultiTypeIdEntity> newData) {
        return new StringDiffCallBack(oldData, newData);
    }
}
