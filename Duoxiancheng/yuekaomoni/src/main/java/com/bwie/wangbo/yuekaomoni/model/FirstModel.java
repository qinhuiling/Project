package com.bwie.wangbo.yuekaomoni.model;

import android.net.Uri;

import com.bwie.wangbo.yuekaomoni.Bean.Bean;
import com.bwie.wangbo.yuekaomoni.api.GitService;
import com.bwie.wangbo.yuekaomoni.view.FirstView;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hasee on 2017/11/21.
 */

public class FirstModel implements FirstModelInter{


    private FirstView firstView;

    public FirstModel(FirstView firstView) {
        this.firstView = firstView;
    }

    @Override
    public void getData(final Inter inter) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://api.svipmovie.com/front/columns/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        GitService gitService = retrofit.create(GitService.class);
        Observable<Bean> jsonData = gitService.getJsonData();
        jsonData.subscribeOn(Schedulers.io())
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
                        List<Bean.RetBean.ListBean> list = bean.getRet().getList();
                        String pic = list.get(0).getPic();
                        Uri u = Uri.parse(pic);
                        inter.getInter(bean,u);
                        for (int i=0;i<list.size();i++){
                            Bean.RetBean.ListBean listBean = list.get(i);
                            firstView.getView((List<Bean.RetBean.ListBean>) listBean);
                            String pic1 = listBean.getPic();
                            String title = listBean.getTitle();
                            String description = listBean.getDescription();
                        }
                    }
                });
    }
}
