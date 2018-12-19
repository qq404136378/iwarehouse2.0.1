package com.prt.baselibrary.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 请叫我张懂 on 2017/8/1.
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;


    public CommonViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }

    public CommonViewHolder setTextView(int viewId, CharSequence sequence) {
        TextView tv = this.getView(viewId);
        tv.setText(sequence);
        return this;
    }

    public CommonViewHolder setImageViewResource(int viewId, int resId) {
        ImageView imageView = this.getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    public CommonViewHolder setImageView(int viewId, String path, ImageLoader imageLoader) {
        ImageView imageView = this.getView(viewId);
        imageLoader.setImage(path, imageView);
        return this;
    }


    public CommonViewHolder setVisibility(int viewId, int visibility) {
        View view = this.getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    public interface ImageLoader {
        void setImage(String path, ImageView view);

    }

}
