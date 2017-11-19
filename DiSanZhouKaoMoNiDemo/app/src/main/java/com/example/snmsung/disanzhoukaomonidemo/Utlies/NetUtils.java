package com.example.snmsung.disanzhoukaomonidemo.Utlies;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SNMSUNG on 2017/11/18.
 */

public class NetUtils {

    public static void downloadFile(String downloadUrl, String path, int blockSize, int startPosition){
        BufferedInputStream bis = null;
        RandomAccessFile raf = null;
        try {
            File f = new File(path);
            if(!f.exists()){
                f.createNewFile();
            }
            URL url = new URL(downloadUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(8000);
            conn.setConnectTimeout(8000);

//            long start = 0;
            if(blockSize > 0){
//                //使用线程id来计算 当前线程的开始位置和结束位置
//                start = blockSize * threadId;
                long end = blockSize + startPosition - 1;
                //多线程下载  需要告诉服务器我要请求的是哪部分内容  需要写在请求头里面
                conn.setRequestProperty("Range", "bytes=" + startPosition + "-" + end);

                Log.i(Thread.currentThread() + "======", "bytes=" + startPosition + "-" + end);
            }


            int code = conn.getResponseCode();
            if(code < 400){
                bis = new BufferedInputStream(conn.getInputStream());
                raf = new RandomAccessFile(f, "rwd");
                //
                raf.seek(startPosition);
                //
                int len = 0;
                byte[] buff = new byte[1024 * 8];
                while((len = bis.read(buff)) != -1){
                    synchronized (NetUtils.class){
                        raf.write(buff, 0, len);
                        if(DialogUtils.PROGRESS<0)
                        {
                            DialogUtils.PROGRESS=0;
                        }

                        DialogUtils.PROGRESS += len;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(raf != null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static int getFileLength(String downloadUrl){
        int len = 0;
        try {
            URL url = new URL(downloadUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(8000);
            conn.setConnectTimeout(8000);

            len = conn.getContentLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return len;
    }



}
