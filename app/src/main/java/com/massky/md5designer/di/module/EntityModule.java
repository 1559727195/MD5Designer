package com.massky.md5designer.di.module;

import com.massky.domain.entity.common.CommonHeaderEntity;
import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.md5designer.ui.adapter.helper.HomeAdapterHelper;

import javax.inject.Named;
import dagger.Module;
import dagger.Provides;

@Module
public class EntityModule {

    public static final String NAME_ZHIHU = "zhihu";
    public static final String NAME_GANK_IO = "gankio";
    public static final String NAME_NEIHAN = "neihan";
    public static final String NAME_GAOXIAO = "gaoxiao";

    @Named(NAME_ZHIHU)
    @Provides
    CommonHeaderEntity providerZhihuHeader() {
        return new CommonHeaderEntity(ZhihuNewsEntity.StoriesEntity.HEADER_TITLE, HomeAdapterHelper.LEVEL_ZHIHU, ZhihuNewsEntity.StoriesEntity.HEADER_TITLE, ZhihuNewsEntity.StoriesEntity.HEADER_OPTIONS);
    }


}
