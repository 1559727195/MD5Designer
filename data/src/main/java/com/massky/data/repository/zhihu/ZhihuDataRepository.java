package com.massky.data.repository.zhihu;

import com.massky.data.api.HttpHelper;
import com.massky.data.service.ZhihuService;
import com.massky.data.util.RxTransformerUtil;
import com.massky.domain.entity.zhihu.ZhihuNewsDetailEntity;
import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.domain.repository.ZhihuRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class ZhihuDataRepository implements ZhihuRepository {
    private ZhihuService mZhihuService;
    @Inject
    public ZhihuDataRepository(HttpHelper helper) {
        mZhihuService = helper.getZhihuService();
    }
    @Override
    public Flowable<ZhihuNewsEntity> getZhihuNewsList() {
        return mZhihuService.getZhihuNewsList()
                .compose(RxTransformerUtil.normalTransformer());
    }

    @Override
    public Flowable<ZhihuNewsDetailEntity> getZhihuNewsDetail(long id) {
        return null;
    }
}
