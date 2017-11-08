package com.bwie.asus.zhoukaoyi.model;

import com.bwie.asus.zhoukaoyi.api.API;
import com.bwie.asus.zhoukaoyi.bean.Bean;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ASUS on 2017/11/7.
 */

public class RetroModel implements RetroModelInterface {
    @Override
    public void retro(final Callback callback) {
        API.api.a().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        List<Bean.SongListBean> bea = bean.getSong_list();
                        callback.ab(bea);
                    }
                });
    }
}
