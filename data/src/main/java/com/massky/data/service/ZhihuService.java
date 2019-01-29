package com.massky.data.service;

import com.massky.domain.entity.zhihu.ZhihuNewsDetailEntity;
import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ZhihuService {
    String HOST = "http://news-at.zhihu.com/api/4/";

//    @Headers("Cache-Control: public, max-age=300")//缓存时间为5分钟
//    @GET("news/latest")
//    Flowable<ZhihuNewsEntity> getZhihuNewsList();
//
//    @Headers("Cache-Control: public, max-age=300")//缓存时间为5分钟
//    @GET("news/{id}")
//    Flowable<ZhihuNewsDetailEntity> getZhihuNewsDetail(@Path("id") long id);

    @Headers("Cache-Control: public, max-age=300")
    @GET("news/latest")
    Flowable<ZhihuNewsEntity> getZhihuNewsList();
}
