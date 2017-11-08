package com.bwie.asus.deblocking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyView myView = (MyView) findViewById(R.id.myview);
        final ImageView iv = (ImageView) findViewById(R.id.iv);

        myView.setOnUnlock(new OnUnlock() {
            @Override
            public void setUnlock(boolean unlock) {
                if (unlock){
                    myView.setVisibility(View.GONE);
                    iv.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
