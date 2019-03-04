package com.massky.data.api;

import android.content.Context;
import android.text.TextUtils;

import com.massky.data.logger.HttpLogger;
import com.massky.data.service.GankioService;
import com.massky.data.service.LoginService;
import com.massky.data.service.ZhihuService;
import com.massky.data.util.LoggerUtil;
import com.massky.data.util.NetworkUtils;
import com.massky.domain.constant.CacheConstant;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class HttpHelper {
    private OkHttpClient mOkHttpClient;
    private volatile ZhihuService mZhihuService;
    private volatile GankioService mGankioService;
    private volatile LoginService mLoginService;
    @Inject
    public HttpHelper(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置缓存 20M
        Cache cache = new Cache(new File(context.getExternalCacheDir(), CacheConstant.CACHE_DIR_API), 20 * 1024 * 1024);
        builder.cache(cache);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        builder.addInterceptor(new CrazyDailyCacheInterceptor());
        builder.addNetworkInterceptor(new CrazyDailyCacheNetworkInterceptor());
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        mOkHttpClient = builder.build();
    }

    /**
     * 有网才会执行哦
     */
    private static class CrazyDailyCacheNetworkInterceptor implements Interceptor {

        private static final String CACHE_CONTROL = "Cache-Control";

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            final Response response = chain.proceed(request);
            final String requestHeader = request.header(CACHE_CONTROL);
            //判断条件最好加上TextUtils.isEmpty(response.header(CACHE_CONTROL))来判断服务器是否返回缓存策略，如果返回，就按服务器的来，我这里全部客户端控制了
            if (!TextUtils.isEmpty(requestHeader)) {
                LoggerUtil.i(LoggerUtil.MSG_HTTP, "CrazyDailyCacheNetworkInterceptor---cache---host:" + request.url().host());
                return response.newBuilder().header(CACHE_CONTROL, requestHeader).removeHeader("Pragma").build();
            }
            return response;
        }
    }

    private static class CrazyDailyCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            CacheControl cacheControl = request.cacheControl();
            //header可控制不走这个逻辑
            boolean noCache = cacheControl.noCache() || cacheControl.noStore() || cacheControl.maxAgeSeconds() == 0;
            if (!noCache && !NetworkUtils.isNetworkAvailable()) {
                Request.Builder builder = request.newBuilder();
                LoggerUtil.i(LoggerUtil.MSG_HTTP, "CrazyDailyCacheInterceptor---cache---host:" + request.url().host());
                CacheControl newCacheControl = new CacheControl.Builder().maxStale(1, TimeUnit.DAYS).build();
                request = builder.cacheControl(newCacheControl).build();
                return chain.proceed(request);
            }
            return chain.proceed(request);
        }
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public ZhihuService getZhihuService() {
        if (mZhihuService == null) {
            synchronized (this) {
                if (mZhihuService == null) {
                    mZhihuService = new Retrofit.Builder()
                            .baseUrl(ZhihuService.HOST)
                            .client(mOkHttpClient)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(ZhihuService.class);
                }
            }
        }
        return mZhihuService;
    }

    public GankioService getGankioService() {
        if (mGankioService == null) {
            synchronized (this) {
                if (mGankioService == null) {
                    mGankioService = new Retrofit.Builder()
                            .baseUrl(GankioService.HOST)
                            .client(mOkHttpClient)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(GankioService.class);
                }
            }
        }
        return mGankioService;
    }


    public LoginService getLoginService() {
        if (mLoginService == null) {
            synchronized (this) {
                if (mLoginService == null) {
                    mLoginService = new Retrofit.Builder()
                            .baseUrl(LoginService.HOST)
                            .client(mOkHttpClient)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(LoginService.class);
                }
            }
        }
        return mLoginService;
    }


}
