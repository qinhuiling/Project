package com.bwie.mcowu.lx_zhouthree.presenter;

import android.content.Context;

import com.bwie.mcowu.lx_zhouthree.model.IDwonFile;
import com.bwie.mcowu.lx_zhouthree.model.IdownModel;
import com.bwie.mcowu.lx_zhouthree.view.IDownView;

import java.lang.ref.SoftReference;

import okhttp3.ResponseBody;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 13:16.
 * 本类作用:
 */

public class DownPresenter implements IPresenter<IDownView> {

    SoftReference<IDownView> srf;
    IdownModel model;
    Context context;

    public DownPresenter(IDownView view, Context context) {
        this.context = context;
        model = new IdownModel(context);
        attch(view);
    }

    public void down(final String start, final String url, final String url2) {
        model.getData(start, url, url2, new IDwonFile.GetCallback() {
            @Override
            public void GetComplete(ResponseBody responseBody) {

            }
        });
    }
    public void stop(){
        model.stop();
    }
    public void jixu(){
        model.jixu();
    }
    public void setProgress(){
       model.setGetProgress(new IdownModel.getProgress() {
           @Override
           public void getProgress_length(long l) {
                srf.get().getPro(l);
           }
       });
    }



    @Override
    public void attch(IDownView view) {
        srf = new SoftReference<IDownView>(view);
    }

    @Override
    public void disattch() {

    }
}
