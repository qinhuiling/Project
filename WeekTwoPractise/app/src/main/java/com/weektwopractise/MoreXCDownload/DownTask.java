package com.weektwopractise.MoreXCDownload;

/**
 * User: 张亚博
 * Date: 2017-11-11 09:00
 * Description：
 */
public class DownTask extends Thread {
    private int DownSize;
    private String DownUrl;
    private int startPosition;
    private String path;

    public DownTask(int downSize, String downUrl, int startPosition, String path) {
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
