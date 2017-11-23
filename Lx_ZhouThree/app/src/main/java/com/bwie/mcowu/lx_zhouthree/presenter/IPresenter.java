package com.bwie.mcowu.lx_zhouthree.presenter;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 09:22.
 * 本类作用:
 */

public  interface IPresenter<T> {
    void attch(T view);
    void disattch();
}
