package com.bwie.mcowu.lx_zhouthree.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bwie.mcowu.lx_zhouthree.presenter.IPresenter;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 09:24.
 * 本类作用:
 */

public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity {
    public T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresnter();
    }
    protected abstract void createPresnter();
    protected abstract void init();
}
