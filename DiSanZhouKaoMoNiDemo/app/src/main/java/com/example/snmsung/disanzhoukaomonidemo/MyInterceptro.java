package com.example.snmsung.disanzhoukaomonidemo;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SNMSUNG on 2017/11/17.
 */

public class MyInterceptro implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request=chain.request();
        Log.i("===url====", "intercept: "+request.url());
        Log.i("====res_headers====", "intercept: "+request.headers());
      Response res=  chain.proceed(request);
        Log.i("=====req_headers===", "intercept: "+res.headers());
        Log.i("====body=======", "intercept: "+res.body().string());
        return res;
    }
}
