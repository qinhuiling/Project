package com.bwie.asus.weeklyexam.model;

import android.content.Context;

/**
 * Created by ASUS on 2017/10/15.
 */

public interface LoginModelInterface {
    void loginModel(Context context, String loginName, String loginPwd);
    void loginRequest(Context context,String loginName,String loginPwd);
}
