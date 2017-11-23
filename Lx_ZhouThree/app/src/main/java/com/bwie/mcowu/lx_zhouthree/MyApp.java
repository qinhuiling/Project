package com.bwie.mcowu.lx_zhouthree;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 09:56.
 * 本类作用:
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
