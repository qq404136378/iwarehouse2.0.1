package com.prt.baselibrary.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 请叫我张懂 on 2017/9/13.
 */

public class CommonItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private Drawable mDrawable;
    //
    private int mTotalItems;//总Item数
    private int mSpanCount;//总列数

    public CommonItemDecoration(Context context, int drawableId) {
        this.mContext = context;
        this.mDrawable = ContextCompat.getDrawable(this.mContext, drawableId);
    }

    /**
     * @param outRect 用于规定分割线的范围
     * @param view    进行分割线操作的子view
     * @param parent  父view
     * @param state   (这里暂时不使用)
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        mTotalItems = parent.getAdapter().getItemCount();
        if (0 == mSpanCount) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            //判断是否为GridLayoutManager
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                mSpanCount = gridLayoutManager.getSpanCount();
            } else {
                mSpanCount = 1;
            }
        }
        //在源码中有一个过时的方法，里面有获取当前ItemPosition
        int currentItemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        //
        if (!isLastRow(currentItemPosition, mTotalItems, mSpanCount)) {
            outRect.bottom = mDrawable.getIntrinsicWidth();
        } else {
            outRect.bottom = 0;
        }
        //
        int averWidth = (mSpanCount - 1) * mDrawable.getIntrinsicWidth() / mSpanCount;
        int dX = mDrawable.getIntrinsicWidth() - averWidth;
        outRect.left = (currentItemPosition % mSpanCount) * dX;
        outRect.right = averWidth - outRect.left;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            drawHorizontalDecoration(c, parent.getChildAt(i));
            drawVerticalDecoration(c, parent.getChildAt(i));
        }
    }

    private void drawHorizontalDecoration(Canvas canvas, View childView) {
        int currentItemPosition = ((RecyclerView.LayoutParams) childView.getLayoutParams()).getViewLayoutPosition();
        if (isLastRow(currentItemPosition, mTotalItems, mSpanCount)) {
            return;
        }
        //
        Rect rect = new Rect(0, 0, 0, 0);

        rect.top = childView.getBottom();
        rect.bottom = rect.top + mDrawable.getIntrinsicWidth();
        rect.left = childView.getLeft();
        rect.right = childView.getRight() + mDrawable.getIntrinsicWidth();

        mDrawable.setBounds(rect);
        mDrawable.draw(canvas);
    }

    private void drawVerticalDecoration(Canvas canvas, View childView) {
        Rect rect = new Rect(0, 0, 0, 0);

        rect.top = childView.getTop();
        rect.bottom = childView.getBottom();
        rect.left = childView.getRight();
        rect.right = rect.left + mDrawable.getIntrinsicWidth();

        mDrawable.setBounds(rect);
        mDrawable.draw(canvas);
    }

    private boolean isLastRow(int currentItemPosition, int totalItems, int spanCount) {
        boolean result = false;
        int rowCount = 0;

        if (0 == totalItems % spanCount) {
            rowCount = totalItems / spanCount;
        } else {
            rowCount = totalItems / spanCount + 1;
        }
        if ((currentItemPosition + 1) > (rowCount - 1) * spanCount) {
            result = true;
        }

        return result;
    }

    private boolean isLastColumn(int currentItemPosition, int spanCount) {
        boolean result = false;
        if (0 == (currentItemPosition + 1) % spanCount) {
            result = true;
        }
        return result;
    }
}
