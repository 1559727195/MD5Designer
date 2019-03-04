package com.massky.data.service;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {

    String HOST = "http://gank.io/api/";
    @Headers({"Content-Type: application/json","Accept: application/json"
            ,"Cache-Control: public,max-age=300"
    })//需要添加头
    @POST("vdyweb/ws/rest/Login")
    Call<ResponseBody> getMessage(@Body RequestBody info);   // 请求体味RequestBody 类型
}
