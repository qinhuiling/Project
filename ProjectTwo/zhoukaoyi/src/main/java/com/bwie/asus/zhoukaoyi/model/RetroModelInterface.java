package com.bwie.asus.zhoukaoyi.model;

import com.bwie.asus.zhoukaoyi.bean.Bean;

import java.util.List;

/**
 * Created by ASUS on 2017/11/7.
 */

public interface RetroModelInterface {
    void retro(Callback callback);
    interface Callback{
        void ab(List<Bean.SongListBean> bean);
    }
}
