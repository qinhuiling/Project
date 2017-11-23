package com.bwie.mcowu.lx_zhouthree.model;

import okhttp3.ResponseBody;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 13:11.
 * 本类作用:
 */

public interface IDwonFile {
    void getData(String start, String url, String url2, GetCallback callback);

    interface GetCallback {
        void GetComplete(ResponseBody responseBody);
    }

    void stop();

    void jixu();

    void getProgress();
}
