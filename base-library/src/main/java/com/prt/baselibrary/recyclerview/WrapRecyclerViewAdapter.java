package com.prt.baselibrary.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 请叫我张懂 on 2017/8/9.
 */

public class WrapRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView.Adapter mAdapter;
    private SparseArray<View> mHeaders;
    private SparseArray<View> mFooters;

    private static int HEADER = 10000;
    private static int FOOTER = 20000;

    public WrapRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        mHeaders = new SparseArray<>();
        mFooters = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaders.indexOfKey(viewType) >= 0) {
            return createViewMyHolder(mHeaders.get(viewType));
        } else if (mFooters.indexOfKey(viewType) >= 0) {
            return createViewMyHolder(mFooters.get(viewType));
        }

        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    private RecyclerView.ViewHolder createViewMyHolder(View view) {

        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mHeaders.size()) {
            return mHeaders.keyAt(position);
        }
        //
        int adjPosition = position - mHeaders.size();
        int adapterCount = mAdapter.getItemCount();
        if (adjPosition < adapterCount) {
            return mAdapter.getItemViewType(adjPosition);
        }
        return mFooters.keyAt(adjPosition - adapterCount);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < mHeaders.size()) {
            return;
        }
        //
        int adjPosition = position - mHeaders.size();
        int adapterCount = mAdapter.getItemCount();
        if (adjPosition < adapterCount) {
            mAdapter.onBindViewHolder(holder, adjPosition);
        }
    }


    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + mHeaders.size() + mFooters.size();
    }

    protected void addHeader(View view) {
        if (mHeaders.indexOfValue(view) == -1) {
            mHeaders.put(HEADER++, view);
            notifyDataSetChanged();
        }
    }

    protected void addFooter(View view) {
        if (mFooters.indexOfValue(view) == -1) {
            mFooters.put(FOOTER++, view);
            notifyDataSetChanged();
        }
    }

    protected void removeHeader(View view) {
        if (mHeaders.indexOfValue(view) >= 0) {
            mHeaders.removeAt(mHeaders.indexOfValue(view));
            notifyDataSetChanged();
        }
    }

    protected void removeFooter(View view) {
        if (mFooters.indexOfValue(view) >= 0) {
            mFooters.removeAt(mFooters.indexOfValue(view));
            notifyDataSetChanged();
        }
    }

}
