package com.bwie.wangbo.yuekaomoni.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.bwie.wangbo.yuekaomoni.Bean.Bean;
import com.bwie.wangbo.yuekaomoni.R;
import com.bwie.wangbo.yuekaomoni.adapter.RecyclerViewAdapter;
import com.bwie.wangbo.yuekaomoni.presenter.FirstPresenter;
import com.bwie.wangbo.yuekaomoni.presenter.FirstPresenterInter;
import com.bwie.wangbo.yuekaomoni.view.FirstView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FirstView{

    private RecyclerView mRecycler;
    private SimpleDraweeView image;
    private RecyclerViewAdapter adapter;
    private LinearLayout lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        FirstPresenterInter presenterInterFace = new FirstPresenter(this);
        presenterInterFace.getPre();
        setLin();

    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        image = (SimpleDraweeView) findViewById(R.id.image);
        lin = (LinearLayout) findViewById(R.id.lin);
    }

    @Override
    public void getView(List<Bean.RetBean.ListBean> bean) {
        adapter = new RecyclerViewAdapter(this,bean);
        mRecycler.setAdapter(adapter);
        adapter.setiOnclick(new RecyclerViewAdapter.IOnclick() {
            @Override
            public void onclick() {
                Intent intent = new Intent();
                intent.setAction("amain");
                startActivity(intent);
            }
        });
    }

    @Override
    public void getPhoto(Uri uri) {
        image.setImageURI(uri);
    }

    private void setLin(){
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
