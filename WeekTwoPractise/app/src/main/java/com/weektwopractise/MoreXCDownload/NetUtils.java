package com.weektwopractise.MoreXCDownload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: 张亚博
 * Date: 2017-11-11 08:49
 * Description：
 */
public class NetUtils {

    /**
     * 获取要下的文件的长度
     * @param downloadUrl
     * @return
     */
    public static int getFileContentLength(String downloadUrl){
        int contentLength=0;
        try {
            URL url=new URL(downloadUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setConnectTimeout(5000);
            //获取下载的长度
           contentLength = con.getContentLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  contentLength;
    }
    public static void DownLoad(String downUrl,int DownSize,String path,int startPosition){
        BufferedInputStream buffer = null;
        RandomAccessFile raf = null;
        try {
            File file=new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            URL url=new URL(downUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            if (DownSize>0) {
                int endPosition=DownSize+startPosition-1;
                con.setRequestProperty("Range","bytes="+startPosition+"-"+endPosition);
            }
            int responseCode = con.getResponseCode();//获取响应码
            if (400>responseCode) {
                buffer = new BufferedInputStream(con.getInputStream());
                raf = new RandomAccessFile(file, "rwd");
                raf.seek(startPosition);
                int len=0;
                byte[] bytes=new byte[1024*8];
                while ((len=buffer.read(bytes))!=-1){
                    synchronized (NetUtils.class){
                        raf.write(bytes,0,len);
                        if (DialogUtils.PROGRESS<0) {
                            DialogUtils.PROGRESS=0;
                        }
                        DialogUtils.PROGRESS+=len;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (buffer!=null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf!=null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
