package com.bwie.asus.download;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bwie.asus.download.net.DialogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        DialogUtils.ShowUpdateProgress(this);

    }
}
