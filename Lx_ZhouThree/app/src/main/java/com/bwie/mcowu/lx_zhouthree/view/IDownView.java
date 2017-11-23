package com.bwie.mcowu.lx_zhouthree.view;

import okhttp3.ResponseBody;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 13:17.
 * 本类作用:
 */

public interface IDownView {
    void getData(ResponseBody responseBody);
    void getPro(long length);
}
