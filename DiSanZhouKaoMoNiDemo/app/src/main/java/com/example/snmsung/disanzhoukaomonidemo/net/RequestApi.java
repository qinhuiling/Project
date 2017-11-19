package com.example.snmsung.disanzhoukaomonidemo.net;


import com.example.snmsung.disanzhoukaomonidemo.Bean.MyBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by SNMSUNG on 2017/11/17.
 */

public interface RequestApi {

    public static final String BASE_URL = " http://result.eolinker.com/";

    @GET("iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=vedio")
    Observable<MyBean> getData();
}
