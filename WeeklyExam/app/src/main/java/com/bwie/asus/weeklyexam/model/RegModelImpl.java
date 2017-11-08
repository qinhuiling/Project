package com.bwie.asus.weeklyexam.model;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.bwie.asus.weeklyexam.api.API;
import com.bwie.asus.weeklyexam.bean.RegBean;
import com.bwie.asus.weeklyexam.port.RegFinishListener;
import com.douya.okhttplibrary.utils.GsonObjectCallback;
import com.douya.okhttplibrary.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by ASUS on 2017/10/15.
 */

public class RegModelImpl implements RegModelInterface {
    private RegFinishListener listener;

    public RegModelImpl(RegFinishListener listener) {
        this.listener = listener;
    }

    @Override
    public void regModel(Context context, String regName, String regPwd) {
        if(TextUtils.isEmpty(regName)){
            listener.regNameEmpty();
            return;
        }
        if(TextUtils.isEmpty(regPwd)){
            listener.regPwdEmpty();
            return;
        }
        regRequest(context,regName, regPwd);
    }

    @Override
    public void regRequest(final Context context, final String regName, String regPwd) {
        //定义一个Map集合，用来存注册的参数
        Map<String,String> regParams=new HashMap<>();
        //将参数添加到集合
        regParams.put("mobile",regName);
        regParams.put("password",regPwd);
        //进行请求
        OkHttp3Utils.doPost(API.REG_PATH, regParams, new GsonObjectCallback<RegBean>() {
            @Override
            public void onUi(RegBean regBean) {
                if(regBean.getCode()==0){
                    listener.regSucceed();
                }
                if(regBean.getCode()==1){
                    Toast.makeText(context,regBean.getMsg(),Toast.LENGTH_SHORT).show();
                    listener.regFailed();
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
