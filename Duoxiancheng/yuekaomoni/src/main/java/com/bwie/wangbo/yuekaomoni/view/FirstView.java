package com.bwie.wangbo.yuekaomoni.view;

import android.net.Uri;

import com.bwie.wangbo.yuekaomoni.Bean.Bean;

import java.util.List;

/**
 * Created by hasee on 2017/11/21.
 */

public interface FirstView {
    void getView(List<Bean.RetBean.ListBean> bean);
    void getPhoto(Uri uri);
}
