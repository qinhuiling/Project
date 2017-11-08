package com.bwie.asus.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Login{

    private EditText username;
    private EditText password;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        presenter = new Presenter(this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString().trim();
                String upwd = password.getText().toString().trim();
                presenter.setLogin(uname,upwd);
            }
        });

    }

    @Override
    public void name() {
        Toast.makeText(this,"name",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwd() {
        Toast.makeText(this,"pwd",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginsuccess(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"result"+result,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
