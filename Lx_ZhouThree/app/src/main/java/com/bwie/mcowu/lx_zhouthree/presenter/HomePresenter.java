package com.bwie.mcowu.lx_zhouthree.presenter;

import com.bwie.mcowu.lx_zhouthree.Bean;
import com.bwie.mcowu.lx_zhouthree.model.HomeModel;
import com.bwie.mcowu.lx_zhouthree.model.IHomeModel;
import com.bwie.mcowu.lx_zhouthree.view.IHomeView;

import java.lang.ref.SoftReference;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 09:26.
 * 本类作用:
 */

public class HomePresenter implements IPresenter<IHomeView> {
    IHomeModel model;
    SoftReference<IHomeView> srf;

    public HomePresenter(IHomeView view) {
        model = new HomeModel();
        attch(view);
    }
    public void show(){
        model.getData(new IHomeModel.HomeCallback() {
            @Override
            public void homeComplete(Bean bean) {
                srf.get().showData(bean);
            }
        });
    }

    @Override
    public void attch(IHomeView view) {
        srf = new SoftReference<IHomeView>(view);
    }

    @Override
    public void disattch() {
        srf =null;
    }
}
