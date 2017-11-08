package com.bwie.asus.zhoukaoyi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.bwie.asus.zhoukaoyi.presenter.RetroPresenterInterface;

/**
 * Created by ASUS on 2017/11/7.
 */

public abstract class PreActivity <T extends RetroPresenterInterface> extends Activity{
    T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        createPresenter();

    }

    abstract void createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (presenter != null){
            presenter.deatch();
        }

    }

}
