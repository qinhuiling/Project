package com.bwie.asus.zhoukaoyi.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.asus.zhoukaoyi.R;
import com.bwie.asus.zhoukaoyi.fragment.Fragment01;
import com.bwie.asus.zhoukaoyi.fragment.Fragment02;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView fanhui;
    private TextView bianji;
    private TabLayout tab;
    private ViewPager vp;

    private List<Fragment> list;
    private String[] datas = {"商品","路线/旅游攻略"};

    private boolean falg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        initView();
        initData();
        initDate();
        BianJi();

    }

    private void initView() {
        fanhui = (ImageView) findViewById(R.id.fanhui);
        bianji = (TextView) findViewById(R.id.bianji);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Fragment01());
        list.add(new Fragment02());
    }

    private void initDate() {
        //设置tab滚动
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置tab和ViewPager联动
        tab.setupWithViewPager(vp);

        vp.setAdapter(new MyTabAdapter(getSupportFragmentManager()));
        //vp.setOffscreenPageLimit(2);
    }

    class MyTabAdapter extends FragmentPagerAdapter{

        public MyTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return datas[position];
        }
    }

    private void BianJi() {
        final SharedPreferences preferences = getSharedPreferences("a", MODE_PRIVATE);
        falg = preferences.getBoolean("falg", false);
        bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (falg) {
                    bianji.setText("完成");
                    preferences.edit().putBoolean("falg", falg).commit();
                    falg = false;
                    initDate();
                } else {
                    bianji.setText("编辑");
                    preferences.edit().putBoolean("falg", falg).commit();
                    falg = true;
                    initDate();
                }
            }
        });
    }

}
