package com.massky.md5designer.di.module;

import com.massky.domain.entity.common.CommonHeaderEntity;
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
    CommonHeaderEntity provideZhihuHeader() {
        return  new CommonHeaderEntity("123",0,"","");
    }

}
