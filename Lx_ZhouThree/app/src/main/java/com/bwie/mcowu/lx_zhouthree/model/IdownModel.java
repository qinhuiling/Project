package com.bwie.mcowu.lx_zhouthree.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bwie.mcowu.lx_zhouthree.Base.BaseService;
import com.bwie.mcowu.lx_zhouthree.Bean2;
import com.bwie.mcowu.lx_zhouthree.Net.GetInterface;
import com.bwie.mcowu.lx_zhouthree.Utils.DBHelper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 13:13.
 * 本类作用:
 */

public class IdownModel implements IDwonFile {
    private static final String DOWNLOAD_INIT = "1";
    private static final String DOWNLOAD_ING = "2";
    private static final String DOWNLOAD_PAUSE = "3";
    public getProgress getProgress;
    private String stateDownload = DOWNLOAD_ING;//当前线程状态
    String url3 = "";
    Context context;
    private long l;

    public IdownModel(Context context) {
        this.context = context;
    }

    @Override
    public void getData(String start, String url, final String url2, final GetCallback callback) {
        this.url3 = url;
        BaseService.createService(GetInterface.class).download(start, url3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        l = responseBody.contentLength();
                        Log.i("=====length=====", "onNext: " + l);
                        downloadFile(l, url2);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void stop() {
        stateDownload = DOWNLOAD_PAUSE;
    }

    @Override
    public void jixu() {
        stateDownload = DOWNLOAD_ING;
    }

    @Override
    public void getProgress() {

    }

    public void downloadFile(long length, String url2) {
        final File file = new File(url2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int threadNum = 5;
        long blockSize = length / threadNum;
        long startPosition = 0;
        long end = 0;
//        //四舍五入---  让一个数字+0。5在四舍五入 就变成 向上取整
        //计算出下载块以后   创建线程执行下载操作
        for (int i = 0; i < threadNum; i++) {
            List<Bean2> cityInfoList = DBHelper.getInstance(context).getCityInfoList(i);
            if (cityInfoList.size() > 0) {
                Long range = cityInfoList.get(0).getRange();
                Long start = cityInfoList.get(0).getStart();
                Long end1 = cityInfoList.get(0).getEnd();
                startPosition = range + start;
                end = end1;
                Log.i("=====", "downloadFile: " + startPosition + "==" + end);
            } else {
                startPosition = blockSize * i;
                //让最后一个线程下载的大小是正好的，  总长度 - 除了最后一个块的大小和
                if (i == threadNum - 1) {
                    blockSize = length - blockSize * (threadNum - 1);
                }
                end = startPosition + blockSize - 1;
                Bean2 bean2 = new Bean2();
                bean2.setThreadNum(i + "");
                bean2.setRange((long) 0);
                bean2.setStart(startPosition);
                bean2.setEnd(end);
                DBHelper.getInstance(context).addToCityInfoTable(bean2);
            }
            downloadTask(startPosition, file, end, length, i);
        }


    }

    public void downloadTask(final long startPosition, final File file, long end, final long length, final int i) {
        //计算开始位置
        String range = "bytes=" + startPosition + "-" + end;
        BaseService.createService(GetInterface.class).download(range, url3)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        BufferedInputStream bis = null;
                        RandomAccessFile raf = null;
                        try {
                            bis = new BufferedInputStream(responseBody.byteStream());
                            raf = new RandomAccessFile(file, "rwd");
                            raf.seek(startPosition);
                            byte[] buff = new byte[1024 * 8];
                            int len = 0;
                            List<Bean2> cityInfoList = DBHelper.getInstance(context).getCityInfoList(i);
                            long length2 = cityInfoList.get(0).getRange();
                            Log.i("=====继续length2=======", "onNext: " + length2);
                            while ((len = bis.read(buff)) != -1) {
                                Log.i("===循环继续====", "onNext: ");
                                if (stateDownload == DOWNLOAD_ING) {
                                    Log.i("===继续下载====", "onNext: ");
                                    length2 += len;
                                    raf.write(buff, 0, len);
                                    handler.sendEmptyMessage((int) len);
                                } else if (stateDownload == DOWNLOAD_PAUSE) {
                                    //更新数据库
                                    DBHelper.getInstance(context).updateInfo(i, length2);
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (bis != null) {
                                try {
                                    bis.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (raf != null) {
                                try {
                                    raf.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public void setGetProgress(IdownModel.getProgress getProgress) {
        this.getProgress = getProgress;
    }

    public interface getProgress {
        void getProgress_length(long l);
    }

    private long downloadedSize = 0;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //每次每个线程回来的长度
            int length = msg.what;
            synchronized (this) {
                downloadedSize += length;
            }
            getProgress.getProgress_length(downloadedSize * 100 / l);
            Log.i("downloadedSize", "handleMessage: " + downloadedSize);
            Log.i("百分比", "handleMessage: " + downloadedSize * 100 / l);
        }
    };
}
