package com.example.snmsung.disanzhoukaomonidemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.example.snmsung.disanzhoukaomonidemo.R;
import com.example.snmsung.disanzhoukaomonidemo.Utlies.DialogUtils;
import com.example.snmsung.disanzhoukaomonidemo.common.PlayerManager;

public class ShiPin extends AppCompatActivity {
    private PlayerManager player;

    private VideoView video_view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_pin);
        Intent intent=getIntent();
      String  vedio_url = intent.getStringExtra("url");


        DialogUtils.showUpdataDialog(this);

    }



}
