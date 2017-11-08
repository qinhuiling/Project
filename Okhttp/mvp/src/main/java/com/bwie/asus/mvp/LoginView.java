package com.bwie.asus.mvp;

/**
 * Created by ASUS on 2017/9/28.
 */

public interface LoginView {
    public void usernameNull();
    public void passwordNull();

    public void loginSuccess(String result);
}
