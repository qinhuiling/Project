package com.bwie.asus.zhoukaoyi.api;

import com.bwie.asus.zhoukaoyi.bean.Bean;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ASUS on 2017/11/7.
 */

public interface API {

    //接口
    @GET("v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0")
    Observable<Bean> a();

    //添加拦截器
    OkHttpClient CLIENT = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

    Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl("http://tingapi.ting.baidu.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(CLIENT)
            .build();

    API api = RETROFIT.create(API.class);

}
