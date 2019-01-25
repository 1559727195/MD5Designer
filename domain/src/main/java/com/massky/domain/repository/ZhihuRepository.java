package com.massky.domain.repository;

import com.massky.domain.entity.zhihu.ZhihuNewsDetailEntity;
import com.massky.domain.entity.zhihu.ZhihuNewsEntity;

import io.reactivex.Flowable;

public interface ZhihuRepository {

    Flowable <ZhihuNewsEntity> getZhihuNewsList();
    Flowable<ZhihuNewsDetailEntity> getZhihuNewsDetail(long id);
}
