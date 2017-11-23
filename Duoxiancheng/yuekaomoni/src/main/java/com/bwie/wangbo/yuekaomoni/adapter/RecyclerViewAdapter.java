package com.bwie.wangbo.yuekaomoni.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.wangbo.yuekaomoni.Bean.Bean;
import com.bwie.wangbo.yuekaomoni.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context context;
    private List<Bean.RetBean.ListBean> songListBeen;
     private Inter inter;

    public RecyclerViewAdapter(Context context, List<Bean.RetBean.ListBean> songListBeen) {
        this.context = context;
        this.songListBeen = songListBeen;
    }

    IOnclick iOnclick;

    public void setiOnclick(IOnclick iOnclick) {
        this.iOnclick = iOnclick;
    }

    public  interface IOnclick{
        void onclick();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public interface Inter{
        void getInter(boolean in);
    }
    public void getFR(Inter inter){
       this.inter = inter;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String pic_small = songListBeen.get(position).getPic();
        holder.im.setImageURI(Uri.parse(pic_small));
        holder.tv.setText(songListBeen.get(position).getTitle());
        holder.down.setText(songListBeen.get(position).getDescription());
        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iOnclick!=null){
                    iOnclick.onclick();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return songListBeen.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv;
        private final SimpleDraweeView im;
        private final TextView down;
        private final LinearLayout lin;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.title);
            im = itemView.findViewById(R.id.image);
            down = itemView.findViewById(R.id.down);
            lin = itemView.findViewById(R.id.lin);
        }
    }
}
