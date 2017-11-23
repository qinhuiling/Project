package com.bwie.mcowu.lx_zhouthree.Net;

import com.bwie.mcowu.lx_zhouthree.Bean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 09:12.
 * 本类作用:
 */

public interface GetInterface {
    @GET("iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=vedio")
    Observable<Bean> getData_Banner();
   /* @Streaming*//*大文件需要加入这个判断，防止下载过程中写入到内存中*//*
    @GET
    Observable<ResponseBody> download(@Header("RANGE") String start, @Url String url);*/
    @Streaming
    @GET
    Observable<ResponseBody> download(@Header("RANGE") String start, @Url String url);
}
