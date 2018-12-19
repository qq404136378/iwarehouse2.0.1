package com.prt.baselibrary.recyclerview;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.prt.baselibrary.R;


/**
 * Created by 请叫我张懂 on 2017/8/1.
 */

public class MyImageLoader implements CommonViewHolder.ImageLoader {
    private static MyImageLoader instance;
    private Context mContext;

    private MyImageLoader(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static MyImageLoader getInstance(Context context) {
        if (instance == null) {
            synchronized (MyImageLoader.class) {
                if (instance == null) {
                    instance = new MyImageLoader(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void setImage(String path, ImageView view) {
        if (view != null) {
            Glide.with(mContext).load(path).fitCenter().placeholder(R.mipmap.ic_place_pic).error(R.mipmap.ic_place_pic).into(view);
        }
    }

}
