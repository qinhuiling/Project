package com.bwie.asus.mvp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2017/9/28.
 */

public class MainActivityModel {

    interface ModelListener{
        public void loginSuccess(String result);
    }

    public void login(String username, String password, final ModelListener listener){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://qhb.2dyt.com/Bwei/login?username=muhanxi&password=111&postkey=1503d").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                listener.loginSuccess(result);
            }
        });
    }

}
