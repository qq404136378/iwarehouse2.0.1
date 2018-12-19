package com.prt.baselibrary.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 请叫我张懂 on 2017/8/1.
 */

abstract public class CommonAdapter<DATA> extends RecyclerView.Adapter<CommonViewHolder> {
    protected Context mContext;
    private List<DATA> mDataList;
    private int mLayoutId;
    private LayoutInflater mInflater;
    private ItemClickListener itemClickListener;
    private ItemClickListener2 itemClickListener2;
    private ItemLongClickListener itemLongClickListener;
    private MultiTypeSupport<DATA> mMultiTypeSupport;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public void setItemClickListener2(ItemClickListener2 itemClickListener2) {
        this.itemClickListener2 = itemClickListener2;
    }
    public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public CommonAdapter(Context context, List<DATA> data, int layoutId) {
        this.mContext = context;
        this.mDataList = data;
        this.mLayoutId = layoutId;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public CommonAdapter(Context context, List<DATA> dataList, MultiTypeSupport<DATA> multiTypeSupport) {
        this(context, dataList, -1);
        this.mMultiTypeSupport = multiTypeSupport;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mMultiTypeSupport != null) {
            mLayoutId = viewType;
        }
        View itemView = mInflater.inflate(mLayoutId, parent, false);
        return new CommonViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiTypeSupport != null) {
            return mMultiTypeSupport.getLayoutId(mDataList.get(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        convert(holder, mDataList.get(position), position);
        //
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position);
                }
            }
            public void onClick2(View v) {
                if (itemClickListener2 != null) {
                    itemClickListener2.onItemClick2(position,v);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position);
                }
            }
        });
        //longClick的时候不响应click
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemLongClickListener != null) {
                    itemLongClickListener.onItemLongClick(position);
                }
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    protected abstract void convert(CommonViewHolder holder, DATA data, int position);

    public interface ItemClickListener {
        void onItemClick(int position);
    }
    public  interface ItemClickListener2{
        void onItemClick2(int position,View view);
    }
    public interface ItemLongClickListener {
        void onItemLongClick(int position);
    }

    public interface MultiTypeSupport<DATA> {
        int getLayoutId(DATA itemData);
    }

}
