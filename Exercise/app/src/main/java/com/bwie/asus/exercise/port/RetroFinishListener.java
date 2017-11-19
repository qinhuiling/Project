package com.bwie.asus.exercise.port;

import com.bwie.asus.exercise.bean.Bean;

import java.util.List;

/**
 * Created by ASUS on 2017/11/18.
 */

public interface RetroFinishListener {
    void onRetroView(List<Bean> bean);
    void onSuccess();
    void onFailed();
}
