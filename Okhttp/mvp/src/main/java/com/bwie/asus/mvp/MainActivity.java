package com.bwie.asus.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginView{

    private EditText eusername;
    private EditText epassword;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eusername = (EditText) findViewById(R.id.username);
        epassword = (EditText) findViewById(R.id.password);

        presenter = new MainActivityPresenter(this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = eusername.getText().toString().trim();
                String password = epassword.getText().toString().trim();

                presenter.login(username,password);

            }
        });

    }

    @Override
    public void usernameNull() {
        Toast.makeText(this,"usernameNull",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passwordNull() {
        Toast.makeText(this,"passwordNull",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"result" + result,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detach();

    }
}
