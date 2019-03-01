package com.massky.data.repository.gankio;

import com.massky.data.api.HttpHelper;
import com.massky.domain.entity.gankio.GankioEntity;
import com.massky.domain.interactor.gankio.GankioRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class GankioDataRepository implements GankioRepository {
    @Override
    public Flowable<GankioEntity> getGankio(String type, int count) {
        return null;
    }

    @Inject
    public GankioDataRepository() {

    }

}
