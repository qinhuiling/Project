package com.bwie.asus.download.net;

/**
 * Created by ASUS on 2017/11/12.
 */

public class DownLoadTask extends Thread {
    private int DownSize;
    private String DownUrl;
    private int startPosition;
    private String path;

    public DownLoadTask(int downSize, String downUrl, int startPosition, String path) {
        DownSize = downSize;
        DownUrl = downUrl;
        this.startPosition = startPosition;
        this.path = path;
    }

    @Override
    public void run() {
        super.run();
        NetUtils.DownLoad(DownUrl,DownSize,path,startPosition);
    }
}
