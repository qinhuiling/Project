package com.bwie.asus.exercise.view;

import com.bwie.asus.exercise.bean.Bean;

import java.util.List;

/**
 * Created by ASUS on 2017/11/18.
 */

public interface RetroView {
    void retroView(List<Bean> bean);
    void success();
    void failed();
}
