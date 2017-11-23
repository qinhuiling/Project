package com.bwie.wangbo.yuekaomoni.api;

import com.bwie.wangbo.yuekaomoni.Bean.Bean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by hasee on 2017/11/21.
 */

public interface GitService {
    @GET("getVideoList.do?catalogId=402834815584e463015584e539330016")
    Observable<Bean> getJsonData();
}
