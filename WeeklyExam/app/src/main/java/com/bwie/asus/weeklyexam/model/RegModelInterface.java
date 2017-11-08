package com.bwie.asus.weeklyexam.model;

import android.content.Context;

/**
 * Created by ASUS on 2017/10/15.
 */

public interface RegModelInterface {
    void regModel(Context context, String regName, String regPwd);
    void regRequest(Context context, String regName, String regPwd);
}
