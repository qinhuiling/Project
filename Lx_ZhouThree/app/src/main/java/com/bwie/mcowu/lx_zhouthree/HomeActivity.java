package com.bwie.mcowu.lx_zhouthree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.bwie.mcowu.lx_zhouthree.Base.BaseActivity;
import com.bwie.mcowu.lx_zhouthree.presenter.HomePresenter;
import com.bwie.mcowu.lx_zhouthree.view.IHomeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<HomePresenter> implements IHomeView {

    private Banner mBanner;
    private RelativeLayout mTitle;
    private RecyclerView mRlv;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        presenter.show();
    }

    @Override
    protected void createPresnter() {
        presenter=new HomePresenter(this);
    }

    @Override
    protected void init() {

    }

    private void initView() {
        mBanner = (Banner) findViewById(R.id.banner);
        mTitle = (RelativeLayout) findViewById(R.id.title);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        list=new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

    @Override
    public void showData(final Bean bean) {
        for (int i = 0; i <bean.getData().size() ; i++) {
            list.add(bean.getData().get(i).getImage_url());
            Log.i("=============", "showData: "+list.toString());
        }
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(list);
        mBanner.start();


        //展示RecyclerView数据
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, bean);
        mRlv.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.onItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                String vedio_url = bean.getData().get(position).getVedio_url();
                Intent intent=new Intent(HomeActivity.this,OtherActivity.class);
                intent.putExtra("vedio_url",vedio_url);
                startActivity(intent);
            }
        });
    }

}
