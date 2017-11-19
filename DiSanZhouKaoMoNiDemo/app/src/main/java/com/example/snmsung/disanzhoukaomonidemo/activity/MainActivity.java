package com.example.snmsung.disanzhoukaomonidemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.snmsung.disanzhoukaomonidemo.Adapter.MyAdapter;
import com.example.snmsung.disanzhoukaomonidemo.Bean.MyBean;
import com.example.snmsung.disanzhoukaomonidemo.ImagGithub.ImgGithub;
import com.example.snmsung.disanzhoukaomonidemo.R;
import com.example.snmsung.disanzhoukaomonidemo.net.RetrofitUtlie;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Banner banner;


    private List<String> list;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<MyBean.DataBean> data1;
    private MyAdapter myAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到控件
        inidata();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void inidata() {

        banner = (Banner) findViewById(R.id.banner);
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        banner.setImageLoader(new ImgGithub());
        list = new ArrayList<>();
        list.add("http://pic32.nipic.com/20130817/9745430_101836881000_2.jpg");
        list.add("http://pic15.nipic.com/20110630/6322714_105943746342_2.jpg");
        list.add("http://pic48.nipic.com/file/20140916/2531170_195153248000_2.jpg");
        list.add("http://img.taopic.com/uploads/allimg/140626/240469-1406261S24553.jpg");
        list.add("http://pic77.nipic.com/file/20150911/21721561_155058651000_2.jpg");
        list.add("http://img4.duitang.com/uploads/item/201603/18/20160318103156_cziuY.jpeg");
        banner.setImages(list);
        banner.start();


        //网络请求
        qingqiu();

    }

    private void qingqiu() {

        RetrofitUtlie.doHttpdeal().getData()
               .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i("======", "onNext: "+data1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i("====12===", "onNext: "+e.toString());
                    }

                    @Override
                    public void onNext(MyBean myBean) {
                        data1 = myBean.getData();
                        Log.i("======3======", "onNext: "+data1);

                        myAdapter1 = new MyAdapter(MainActivity.this, data1);
                        recyclerView.setAdapter(myAdapter1);
                        myAdapter1.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {


                                Intent intent=new Intent(MainActivity.this,ShiPin.class);
                                startActivity(intent);
                            }
                        });
                    }
                });


    }
}
