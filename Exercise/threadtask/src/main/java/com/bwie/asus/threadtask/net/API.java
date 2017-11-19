package com.bwie.asus.threadtask.net;

import com.bwie.asus.threadtask.bean.Bean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * Created by ASUS on 2017/11/18.
 */

public interface API {

    public static final String BEAN = "http://result.eolinker.com/";

    @GET("iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=vedio")
    Observable<Bean> getBean();

    @Streaming
    @POST("{fileName}")
    Call<ResponseBody> downloadFile(@Path("fileName") String fileName, @Header("Range") String range);

    @Streaming
    @POST("{fileName}")
    Call<ResponseBody> getFileLength(@Path("fileName") String fileName);

}
