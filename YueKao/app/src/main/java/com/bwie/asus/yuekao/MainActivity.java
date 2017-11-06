
package com.bwie.asus.yuekao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bwie.asus.aaa.MyApp;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);

    }

    public void rotateyAnimRun(View view){

        MyApp myApp = new MyApp();
        myApp.rotateyAnimRun(iv);

    }

}
