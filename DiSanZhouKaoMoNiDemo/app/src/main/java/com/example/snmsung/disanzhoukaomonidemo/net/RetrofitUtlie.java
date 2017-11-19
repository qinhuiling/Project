package com.example.snmsung.disanzhoukaomonidemo.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SNMSUNG on 2017/11/17.
 */

public class RetrofitUtlie {
    public static RequestApi doHttpdeal() {
        OkHttpClient Client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                //.addNetworkInterceptor(new MyInterceptro())
                .build();



        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(Client)
                .baseUrl(RequestApi.BASE_URL)
                .build();
        RequestApi api = retrofit.create(RequestApi.class);
        return api;
    }


}
