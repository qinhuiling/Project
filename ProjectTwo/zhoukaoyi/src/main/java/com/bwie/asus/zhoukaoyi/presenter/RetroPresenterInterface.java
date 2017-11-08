package com.bwie.asus.zhoukaoyi.presenter;

/**
 * Created by ASUS on 2017/11/7.
 */

public interface RetroPresenterInterface<T> {
    void atteach(T view);
    void deatch();
}
