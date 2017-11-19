package com.bwie.asus.threadtask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwie.asus.threadtask.adapter.RecyAdapter;
import com.bwie.asus.threadtask.banner.BannerImageLoader;
import com.bwie.asus.threadtask.bean.Bean;
import com.bwie.asus.threadtask.net.Utils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Banner banner;
    private RecyclerView recy;
    private RecyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        recy = (RecyclerView) findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        Utils.download().getBean().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        final List<Bean.DataBean> data = bean.getData();

                        List<String> image_url = new ArrayList<String>();
                        List<String> content = new ArrayList<String>();
                        for (int i = 0; i < data.size(); i++) {
                            image_url.add(data.get(i).getImage_url());
                            content.add(data.get(i).getContent());
                        }
                        //设置轮播样式
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                        //设置图片加载器
                        banner.setImageLoader(new BannerImageLoader());
                        //设置图片集合
                        banner.setImages(image_url);
                        //设置轮播标题集合
                        banner.setBannerTitles(content);
                        //设置轮播时间
                        banner.setDelayTime(3000);

                        banner.start();

                        adapter = new RecyAdapter(MainActivity.this, data);
                        recy.setAdapter(adapter);

                        adapter.setOnItemClickListener(new RecyAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {



                            }
                        });

                    }
                });

    }

}
