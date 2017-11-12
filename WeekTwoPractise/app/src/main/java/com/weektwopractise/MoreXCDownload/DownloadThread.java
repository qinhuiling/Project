package com.weektwopractise.MoreXCDownload;

import android.annotation.SuppressLint;
import android.util.Log;

/**
 * User: 张亚博
 * Date: 2017-11-11 08:42
 * Description：
 */
public class DownloadThread extends Thread{
    private String downUrl="";
    private int ThreadNum=5;
    private String path;

    public DownloadThread(String downUrl,String path,int threadNum){
        this.downUrl=downUrl;
        this.path=path;
        this.ThreadNum=threadNum;
    }
    @SuppressLint("LongLogTag")
    @Override
    public void run() {
        super.run();

        //获取要下载文件的长度，即大小
        int fileLength = NetUtils.getFileContentLength(downUrl);
        DialogUtils.MAX=fileLength;
        Log.i("=============DownLoadThread===============", "fileLength: "+fileLength);
        int DownSize=fileLength/ThreadNum;
        for (int i=0;i<ThreadNum;i++){
            int startPosition=i*DownSize;
            if (i==ThreadNum-1) {
                DownSize=fileLength-DownSize*(ThreadNum-1);
            }
            new DownTask(DownSize,downUrl,startPosition,path).start();
        }

    }
}
