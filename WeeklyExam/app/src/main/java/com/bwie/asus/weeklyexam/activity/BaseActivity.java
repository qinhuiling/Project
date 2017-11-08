package com.bwie.asus.weeklyexam.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.asus.weeklyexam.R;
import com.bwie.asus.weeklyexam.api.API;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //初始化SharedPreferences
        API.init(getApplicationContext());

    }
}
