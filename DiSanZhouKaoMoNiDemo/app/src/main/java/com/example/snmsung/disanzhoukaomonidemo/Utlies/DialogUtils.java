package com.example.snmsung.disanzhoukaomonidemo.Utlies;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import static android.content.Context.MODE_APPEND;

/**
 * Created by SNMSUNG on 2017/11/18.
 */

public class DialogUtils {
    private Context context;
    public static long MAX_SIZE = 0;
    public static long PROGRESS = -2;


    public static void showUpdataDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("是否更新");
        builder.setMessage("太旧了，更新吧");
        builder.setNegativeButton("就不", null);
        builder.setPositiveButton("可以", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("user", MODE_APPEND);
                String userr = sharedPreferences.getString("url", null);
                Log.i("=======asdsasdsa=======", "onClick: " + userr);
                new DownLoadThread(userr, context.getCacheDir() + "12312313123.mp4").start();
                showProgress(context);
            }
        });
        builder.show();
    }

    public static void showProgress(final Context context) {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setTitle("正在更新");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(100);
        pd.show();

        new AsyncTask<String, Integer, String>() {

            @Override
            protected String doInBackground(String... strings) {
                while (PROGRESS + 1 < MAX_SIZE) {
                    SystemClock.sleep(100);
                    if (MAX_SIZE > 0) {
                        publishProgress((int) (PROGRESS * 100 / MAX_SIZE));
                    }


                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pd.dismiss();
//                File file = new File(context.getCacheDir() + "/taobao_161.apk");
//                String[] command = {"chmod", "777", file.getPath() };
//                ProcessBuilder builder = new ProcessBuilder(command);
//                try {
//                    builder.start();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//// 由于没有在Activity环境下启动Activity,设置下面的标签
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setDataAndType(Uri.fromFile(file)),
//                        "application/vnd.android.package-archive"),
//                context.startActivity(intent);


                File file = new File(context.getCacheDir() + "/app-release.apk");

                String command = "chmod " + "777" + " " + file.getPath();
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec(command);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(Intent.ACTION_VIEW);
// 由于没有在Activity环境下启动Activity,设置下面的标签
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(file),
                        "application/vnd.android.package-archive");
                context.startActivity(intent);

            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                pd.setProgress(values[0]);
            }
        }.execute();
    }

}
