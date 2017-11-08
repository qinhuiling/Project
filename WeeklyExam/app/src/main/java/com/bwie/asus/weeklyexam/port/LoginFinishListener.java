package com.bwie.asus.weeklyexam.port;

/**
 * Created by ASUS on 2017/10/15.
 */

public interface LoginFinishListener {
    void loginNameEmpty();
    void loginPwdEmpty();
    void loginSucceed();
    void loginFailed();
}
