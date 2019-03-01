package com.massky.domain.interactor.gankio;

import com.massky.domain.entity.gankio.GankioEntity;

import io.reactivex.Flowable;

public interface GankioRepository {

    Flowable<GankioEntity> getGankio(String type,int count);
}
