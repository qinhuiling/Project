package com.example.snmsung.disanzhoukaomonidemo.Utlies;

/**
 * Created by SNMSUNG on 2017/11/18.
 */

public class DownLoadThread extends  Thread{

    private String downLoadUrl = "";
    private String path;
    private int threadNum = 5;

    public DownLoadThread(String downLoadUrl, String path) {
        this.downLoadUrl = downLoadUrl;
        this.path = path;
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        int len = NetUtils.getFileLength(downLoadUrl);
        DialogUtils.MAX_SIZE = len;
        int blockSize = len / threadNum;
        for (int i = 0; i < threadNum; i++) {
            int startPosition = i * blockSize;
            if (i == threadNum - 1) {
                blockSize = len - blockSize * (threadNum - 1);
            }
            new DownLoadTask(downLoadUrl, path, blockSize, startPosition).start();
        }
    }
}
