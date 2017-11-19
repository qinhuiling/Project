package com.bwie.asus.exercise.model;

import android.content.Context;

import com.bwie.asus.exercise.bean.Bean;
import com.bwie.asus.exercise.net.Utils;
import com.bwie.asus.exercise.port.RetroFinishListener;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ASUS on 2017/11/18.
 */

public class ModelImpl implements ModelInterface {

    private RetroFinishListener listener;

    public ModelImpl(RetroFinishListener listener) {
        this.listener = listener;
    }

    @Override
    public void model(Context context, List<Bean> bean) {
        if (bean == null){
            listener.onRetroView(bean);
        }
        modelInterface(context,bean);
    }

    @Override
    public void modelInterface(Context context, List<Bean> bean) {
        Utils.download().getBean().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        List<Bean.DataBean> data = bean.getData();

                    }
                });

    }

}
