package com.bwie.mcowu.lx_zhouthree.model;

import com.bwie.mcowu.lx_zhouthree.Base.BaseService;
import com.bwie.mcowu.lx_zhouthree.Bean;
import com.bwie.mcowu.lx_zhouthree.Net.GetInterface;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 09:17.
 * 本类作用:
 */

public class HomeModel implements IHomeModel {
    @Override
    public void getData(final HomeCallback callback) {
        BaseService.createService(GetInterface.class).getData_Banner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        callback.homeComplete(bean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
