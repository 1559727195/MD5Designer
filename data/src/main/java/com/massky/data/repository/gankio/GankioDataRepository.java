package com.massky.data.repository.gankio;

import com.massky.data.api.HttpHelper;
import com.massky.data.service.GankioService;
import com.massky.data.util.RxTransformerUtil;
import com.massky.domain.entity.gankio.GankioEntity;
import com.massky.domain.interactor.gankio.GankioRepository;
import javax.inject.Inject;
import io.reactivex.Flowable;

public class GankioDataRepository implements GankioRepository {
    private GankioService mGankioService;

    @Override
    public Flowable<GankioEntity> getGankio(String type, int count) {
        return mGankioService.getGankio(type, count).
                compose(RxTransformerUtil.normalTransformer());
    }

    @Inject
    public GankioDataRepository(HttpHelper httpHelper) {
        mGankioService = httpHelper.getGankioService();
    }
}
