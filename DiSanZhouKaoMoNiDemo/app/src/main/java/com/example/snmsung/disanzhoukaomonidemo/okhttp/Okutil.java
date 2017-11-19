package com.example.snmsung.disanzhoukaomonidemo.okhttp;

import android.os.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 王过 on 2017/10/16.
 */
public class Okutil {
    private Handler handler=new Handler();
    public Handler gerhanlder(){
        return handler;
    }
    private static Okutil okutil=new Okutil();
    private Okutil(){};
    public static Okutil getinstans(){
        return okutil;
    }
    private OkHttpClient okHttpClient;
    public void init(){
        if (okHttpClient==null){
            okHttpClient=new OkHttpClient.Builder().build();
        }
    }
    public void doget(String url, Callback callback){
        init();

        Request request=new Request.Builder().addHeader("User-Agent","").url(url).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
