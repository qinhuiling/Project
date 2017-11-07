package com.bwie.asus.projecttwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView tv2;
    private TextView tv1;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        initData();

    }

    private void initView() {
        button = (Button) findViewById(R.id.button);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
    }

    private void initData() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String s1 = intent.getStringExtra("s1");
                String s2 = intent.getStringExtra("s2");
                tv1.setText("账号:" + s1);
                tv2.setText("密码:" + s2);
            }
        });

    }

}
