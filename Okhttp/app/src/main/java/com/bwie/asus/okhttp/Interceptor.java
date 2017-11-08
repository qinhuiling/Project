package com.bwie.asus.okhttp;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2017/9/27.
 */

public class Interceptor implements okhttp3.Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        long t1 = System.currentTimeMillis();


        Response response = chain.proceed(request);

        long t2 = System.currentTimeMillis();

        System.out.println("t2 = " +( t2-t1));

        return response;
    }
}
