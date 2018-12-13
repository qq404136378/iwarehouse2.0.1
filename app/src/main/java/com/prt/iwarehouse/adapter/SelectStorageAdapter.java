package com.prt.iwarehouse.adapter;

import android.content.Context;

import com.prt.baselibrary.recyclerview.CommonAdapter;
import com.prt.baselibrary.recyclerview.CommonViewHolder;
import com.prt.iwarehouse.R;
import com.prt.iwarehouse.pojo.StorageList;

import java.util.List;

/**
 * Created by 锴锴兴 on 2018/12/13.
 */

public class SelectStorageAdapter extends CommonAdapter<StorageList>{

    public SelectStorageAdapter(Context context, List<StorageList> list, int layoutId) {
        super(context, list, layoutId);
    }

    @Override
    protected void convert(CommonViewHolder holder, StorageList s, int position) {
        holder.setTextView(R.id.item_select_storage_tv_storage,s.getName());
    }
}
