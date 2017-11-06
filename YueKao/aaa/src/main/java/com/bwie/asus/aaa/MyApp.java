package com.bwie.asus.aaa;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by ASUS on 2017/10/26.
 */

public class MyApp {

    public void rotateyAnimRun(View view){

        ObjectAnimator.ofFloat(view,"rotationX",0.0f,360.0f).setDuration(500).start();


    }

}
