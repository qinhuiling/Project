package com.bwie.asus.weeklyexam.presenter;

import android.content.Context;

import com.bwie.asus.weeklyexam.model.RegModelImpl;
import com.bwie.asus.weeklyexam.port.RegFinishListener;
import com.bwie.asus.weeklyexam.view.RegViewInterface;

/**
 * Created by ASUS on 2017/10/15.
 */

public class RegPresenterImpl implements RegPresenterInterface,RegFinishListener{
    private RegViewInterface regViewInterface;
    private final RegModelImpl regModel;

    public RegPresenterImpl(RegViewInterface regViewInterface) {
        this.regViewInterface = regViewInterface;
        regModel = new RegModelImpl(this);
    }

    @Override
    public void regGetData(Context context, String regName, String regPwd) {
        if(regModel!=null){
            regModel.regModel(context, regName, regPwd);
        }

    }

    @Override
    public void regNameEmpty() {
        if(regViewInterface!=null){
            regViewInterface.onRegNameEmpty();
        }
    }

    @Override
    public void regPwdEmpty() {
        if(regViewInterface!=null){
            regViewInterface.onRegPwdEmpty();
        }
    }

    @Override
    public void regSucceed() {
        if(regViewInterface!=null){
            regViewInterface.onRegSucceed();
        }
    }

    @Override
    public void regFailed() {
        if(regViewInterface!=null){
            regViewInterface.onRegFailed();
        }
    }
}
