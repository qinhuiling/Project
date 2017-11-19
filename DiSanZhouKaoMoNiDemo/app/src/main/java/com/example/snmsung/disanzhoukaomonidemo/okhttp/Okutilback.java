package com.example.snmsung.disanzhoukaomonidemo.okhttp;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 王过 on 2017/10/17.
 */
public abstract class Okutilback implements Callback{
    private Handler handler=Okutil.getinstans().gerhanlder();
    public abstract void OnFild(Call call, IOException e);
    public abstract void OnSuccess(String request) throws Exception;

    @Override
    public void onFailure(final Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                OnFild(call, e);
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String str=response.body().string();
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    OnSuccess(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
