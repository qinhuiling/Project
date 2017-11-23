package com.bwie.wangbo.duoxiancheng;

/**
 * Created by hasee on 2017/11/21.
 */

public interface DownloadListener {

     void onFinished();
     void onProgress(float progress);
     void onPause();
     void onCancel();
}
