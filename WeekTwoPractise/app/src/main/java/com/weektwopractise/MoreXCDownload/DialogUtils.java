package com.weektwopractise.MoreXCDownload;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * User: 张亚博
 * Date: 2017-11-11 09:23
 * Description：
 */
public class DialogUtils {
    public static int MAX=0;
    public static int PROGRESS=-2;
    public static void ShowUpdateProgress(final Context context){
        AlertDialog builder=new AlertDialog.Builder(context)
                .setTitle("版本有点低，请选择更新最新版本")
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        new DownloadThread("http://169.254.35.159:8080/wyq/jinritoutiao_460.apk",context.getCacheDir()+"/d.apk",4).start();
                        showDownProgress(context,context.getCacheDir()+"/d.apk");
                    }
                })
                .setNegativeButton("不用了",null)
                .show();


    }

    private static void showDownProgress(final Context context, final String fileurl) {

        final ProgressDialog dialog=new ProgressDialog(context);
        dialog.setTitle("正在更新");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);
        dialog.show();
        new AsyncTask<String, Integer, String>() {
            @SuppressLint("LongLogTag")
            @Override
            protected String doInBackground(String... strings) {
                Log.i("==========DialogUtils========", "doInBackground:+Progress: "+PROGRESS+"Max:"+MAX);
                while (PROGRESS<MAX) {
                    Log.i("==========DialogUtils========", "doInBackground:+Progress: "+PROGRESS+"Max:"+MAX);
                    SystemClock.sleep(100);
                    if (MAX > 0) {

                        publishProgress((int) (PROGRESS * 100 / MAX));
                    } else {
                        publishProgress(0);
                    }

                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                File file=new File(fileurl);
                //重新给下载的文件设置权限
                String[] command = {"chmod", "777", file.getPath() };
                ProcessBuilder builder = new ProcessBuilder(command);
                try {
                    builder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //设置跳转到系统安装页面
                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.addCategory(Intent.CATEGORY_DEFAULT);

                intent.setDataAndType(Uri.parse("file://"+fileurl),"application/vnd.android.package-archive");
                context.startActivity(intent);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                dialog.setProgress(values[0]);
            }
        }.execute();
    }
}
