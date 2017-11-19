package com.example.snmsung.disanzhoukaomonidemo.ImagGithub;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;


/**
 * Created by samsung on 2017/10/13.
 */

public class ImgGithub extends ImageLoader{

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
