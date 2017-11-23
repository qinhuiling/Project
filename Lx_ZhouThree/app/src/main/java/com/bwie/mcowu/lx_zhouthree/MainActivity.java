package com.bwie.mcowu.lx_zhouthree;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStart;
    private Button mAdd;
    private Button mJian;
    private MyView mMyView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }, 6000);
    }

    private void initView() {
        mStart = (Button) findViewById(R.id.start);
        mStart.setOnClickListener(this);
        mAdd = (Button) findViewById(R.id.add);
        mAdd.setOnClickListener(this);
        mJian = (Button) findViewById(R.id.jian);
        mJian.setOnClickListener(this);
        mMyView = (MyView) findViewById(R.id.myView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                mMyView.setColor(Color.BLUE);
                break;
            case R.id.add:
                mMyView.speed();
                break;
            case R.id.jian:
                mMyView.slowDown();
                break;
            default:
                break;
        }
    }
}
