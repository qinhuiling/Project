package com.bwie.asus.tuisong;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ASUS on 2017/9/10.
 */

public class Aaa extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(this);
    }
}
