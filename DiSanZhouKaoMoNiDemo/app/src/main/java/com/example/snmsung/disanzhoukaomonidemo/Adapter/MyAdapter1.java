package com.example.snmsung.disanzhoukaomonidemo.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.snmsung.disanzhoukaomonidemo.Bean.MyBean;
import com.example.snmsung.disanzhoukaomonidemo.R;

import java.util.List;

import static android.R.id.list;

/**
 * Created by SNMSUNG on 2017/11/18.
 */

public class MyAdapter1 extends BaseAdapter{
  List<MyBean.DataBean> list1;
    private Context context;
    private MyViewHolder myViewHolder;

    public MyAdapter1(Context context, List<MyBean.DataBean> list1) {
        this.context=context;
        this.list1=list1;
    }

    @Override
    public int getCount() {
        return list1==null?0:list1.size();
    }

    @Override
    public Object getItem(int i) {
        return list1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView==null){
            convertView=View.inflate(context, R.layout.item1,null);
            myViewHolder = new MyViewHolder();
            myViewHolder.imageView=(ImageView) convertView.findViewById(R.id.img1);
            convertView.setTag(myViewHolder);
        }else{
            myViewHolder= (MyViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list1.get(i).getImage_url()).into(myViewHolder.imageView);
        return convertView;
    }

    public  class MyViewHolder{

        ImageView imageView;

    }
}
