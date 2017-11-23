package com.bwie.wangbo.yuekaomoni.presenter;

import android.net.Uri;

import com.bwie.wangbo.yuekaomoni.Bean.Bean;
import com.bwie.wangbo.yuekaomoni.model.FirstModel;
import com.bwie.wangbo.yuekaomoni.model.FirstModelInter;
import com.bwie.wangbo.yuekaomoni.model.Inter;
import com.bwie.wangbo.yuekaomoni.view.FirstView;

import java.util.List;

/**
 * Created by hasee on 2017/11/21.
 */

public class FirstPresenter implements FirstPresenterInter{

    FirstModelInter firstModelInter;
    FirstView firstView;

    public FirstPresenter(FirstView firstView){
        this.firstView = firstView;
        firstModelInter = new FirstModel(firstView);
    }
    @Override
    public void getPre() {
        firstModelInter.getData(new Inter() {
            @Override
            public void getInter(Bean bean, Uri uri) {
                List<Bean.RetBean.ListBean> data = bean.getRet().getList();
                firstView.getView(data);
                firstView.getPhoto(uri);
            }
        });
    }
}
