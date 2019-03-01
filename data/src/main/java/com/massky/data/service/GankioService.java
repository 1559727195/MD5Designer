package com.massky.data.service;

import com.massky.domain.entity.gankio.GankioEntity;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GankioService {
    String HOST = "http://gank.io/api/";

    @Headers("Cache-Control: public,max-age=300")
    @GET("random/data/{type}/{count}")
    default Flowable<GankioEntity> getGankio(@Path("type") String type,
                                             @Path("count") int count) {
        return null;
    }
}
