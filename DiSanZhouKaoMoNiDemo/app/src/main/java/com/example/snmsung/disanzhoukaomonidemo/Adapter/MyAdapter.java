package com.example.snmsung.disanzhoukaomonidemo.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.snmsung.disanzhoukaomonidemo.Bean.MyBean;
import com.example.snmsung.disanzhoukaomonidemo.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by SNMSUNG on 2017/11/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> implements View.OnClickListener {
    private Context context;
    private List<MyBean.DataBean> data;
    private MyHolder myHolder;
    private OnItemClickListener mOnItemClickListener1 = null;
    private OnItemClickListener mOnItemClickListener = null;


    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
            SharedPreferences sfp=context.getSharedPreferences("user",MODE_PRIVATE);
            sfp.edit().putString("url",data.get((int) view.getTag()).getVedio_url()).commit();
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public MyAdapter(Context context, List<MyBean.DataBean> list1) {
        this.context = context;
        this.data = list1;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        myHolder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textView.setText(data.get(position).getTitle());
        Glide.with(context).load(data.get(position).getImage_url()).into(holder.imageView);
        //将position保存在itemView的Tag中，以便点击时进行获取
        myHolder.itemView.setTag(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }


}
