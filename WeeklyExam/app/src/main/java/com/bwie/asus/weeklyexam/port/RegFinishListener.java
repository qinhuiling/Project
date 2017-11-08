package com.bwie.asus.weeklyexam.port;

/**
 * Created by ASUS on 2017/10/15.
 */

public interface RegFinishListener {
    void regNameEmpty();
    void regPwdEmpty();
    void regSucceed();
    void regFailed();
}
