package com.example.snmsung.disanzhoukaomonidemo.Utlies;

/**
 * Created by SNMSUNG on 2017/11/18.
 */

public class DownLoadTask extends  Thread{
    String downLoadUrl;
    String path;
    int blockSize;
    int startPosition;

    public DownLoadTask(String downLoadUrl, String path, int blockSize, int startPosition) {
        this.downLoadUrl = downLoadUrl;
        this.path = path;
        this.blockSize = blockSize;
        this.startPosition = startPosition;
    }

    @Override
    public void run() {
        super.run();
        NetUtils.downloadFile(downLoadUrl,path,blockSize,startPosition);
    }
}
