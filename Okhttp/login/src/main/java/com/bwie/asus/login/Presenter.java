package com.bwie.asus.login;

import android.text.TextUtils;

/**
 * Created by ASUS on 2017/10/8.
 */

public class Presenter {

    private Login login;
    private Model model;

    public Presenter(Login login) {
        this.login = login;
        this.model = new Model();
    }

    public void setLogin(String username,String password){
        if (TextUtils.isEmpty(username)){
            login.name();
            return;
        }
        if (TextUtils.isEmpty(password)){
            login.pwd();
            return;
        }

        model.setLogin(username, password, new Model.ModelList() {
            @Override
            public void loginsuccess(String result) {
                login.loginsuccess(result);
            }
        });

    }

}
