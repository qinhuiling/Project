package com.bwie.asus.exercise.net;


import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by ASUS on 2017/11/18.
 */

public class DownloadThread extends Thread {
    private String downloadUrl = "";
    private String path;
    private int threadNum = 5;

    public DownloadThread(String downloadUrl, String path) {
        this.downloadUrl = downloadUrl;
        this.path = path;
    }

    @Override
    public void run() {

//        Utils.download().getFileLength(downloadUrl).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                String length = response.headers().get("Content-Length");
//                downloadTask(Integer.parseInt(length));
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });

    }

    public void downloadTask(int length){
        final File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DialogUtils.MAX_SIZE = length;

        //例如  1000kb  3   333
        long blockSize = length/threadNum;
//        //四舍五入---  让一个数字+0。5在四舍五入 就变成 向上取整
//        int blockSize = (int) Math.round(tempSize + 0.5);

        //计算出下载块以后   创建线程执行下载操作
        for (int i = 0; i < threadNum; i++) {
            //计算开始位置
            final long startPosition = blockSize * i;
            //让最后一个线程下载的大小是正好的，  总长度 - 除了最后一个块的大小和
            if(i == threadNum - 1){
                blockSize = length - blockSize * (threadNum - 1);
            }
//            new DownloadTask(downloadUrl, path, blockSize, startPosition).start();


            //"Range:bytes=0-100"
            String range = "bytes=" + startPosition + "-" + (startPosition + blockSize - 1);
            //对于retrofit的网络请求  需要  请求地址 需要设置请求头Range
            Utils.download().downloadFile(downloadUrl, range).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                    if(response.isSuccessful()){
                        try {
                            BufferedInputStream bis = new BufferedInputStream(response.body().byteStream());
                            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                            raf.seek(startPosition);
                            byte[] buff = new byte[1024*8];
                            int len = 0;
                            while((len = bis.read(buff)) != -1){
                                raf.write(buff, 0 , len);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Log.i("=====onResponse=====", "onResponse: " + "失败");
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
    }

    public void setThreadNum(int threadNum){
        this.threadNum = threadNum;
    }

}
