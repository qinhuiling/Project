package com.bwie.mcowu.lx_zhouthree;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 姓名：McoWu
 * 时间:2017/11/18 09:53.
 * 本类作用:
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    Context context;
    Bean bean;

    public MyRecyclerViewAdapter(Context context, Bean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = View.inflate(context, R.layout.items, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnItemClick(view, (Integer) view.getTag());
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(bean.getData().get(position).getTitle());
        holder.tv2.setText(bean.getData().get(position).getContent());
        holder.sdw.setController(Fresco.newDraweeControllerBuilder().setUri(bean.getData().get(position).getImage_url()).build());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return bean.getData() == null ? 0 : bean.getData().size();
    }

    onItemClickListener listener;
    public void setOnItemClickListener(onItemClickListener listener){
        this.listener=listener;
    }
    interface onItemClickListener {
        void OnItemClick(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdw;
        TextView tv, tv2;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdw = itemView.findViewById(R.id.my_image_view);
            tv = itemView.findViewById(R.id.tv_title);
            tv2 = itemView.findViewById(R.id.tv_other);
        }


    }
}

