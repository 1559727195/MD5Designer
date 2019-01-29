package com.massky.data.repository.zhihu;

import com.massky.data.api.HttpHelper;
import com.massky.domain.entity.zhihu.ZhihuNewsDetailEntity;
import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.domain.repository.ZhihuRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class ZhihuDataRepository implements ZhihuRepository {

    @Inject
    public ZhihuDataRepository(HttpHelper helper) {

    }
    @Override
    public Flowable<ZhihuNewsEntity> getZhihuNewsList() {
        return null;
    }

    @Override
    public Flowable<ZhihuNewsDetailEntity> getZhihuNewsDetail(long id) {
        return null;
    }
}
