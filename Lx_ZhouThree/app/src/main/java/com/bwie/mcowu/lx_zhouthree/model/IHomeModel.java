package com.bwie.mcowu.lx_zhouthree.model;

import com.bwie.mcowu.lx_zhouthree.Bean;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 09:12.
 * 本类作用:
 */

public interface IHomeModel {
    void getData(HomeCallback callback);
    interface HomeCallback{
        void homeComplete(Bean bean);
    }
}
