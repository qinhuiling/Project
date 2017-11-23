package com.bwie.wangbo.duoxiancheng;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    private VideoView video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        video = (VideoView) findViewById(R.id.video);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Uri uri = Uri.parse(url);
        video.setMediaController(new MediaController(this));
        video.setVideoURI(uri);
        video.requestFocus();
    }
}
