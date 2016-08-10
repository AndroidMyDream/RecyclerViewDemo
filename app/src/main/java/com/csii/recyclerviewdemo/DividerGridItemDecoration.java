package com.csii.recyclerviewdemo;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

/**
 * Created by 李冬冬 on 2016/8/9.
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mVerticalDivider;
    private Drawable mHorizontalDivider;

    public DividerGridItemDecoration(Drawable mVerticalDivider, Drawable mHorizontalDivider) {

        this.mVerticalDivider = mVerticalDivider;
        this.mHorizontalDivider = mHorizontalDivider;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {

        drawVertical(c, parent);
        drawHorizontal(c, parent);

    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + mHorizontalDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mVerticalDivider.getIntrinsicHeight();
            mVerticalDivider.setBounds(left, top, right, bottom);
            mVerticalDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mHorizontalDivider.getIntrinsicWidth();

            mHorizontalDivider.setBounds(left, top, right, bottom);
            mHorizontalDivider.draw(c);
        }
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                if(getColumNum(pos,spanCount)  == getColum(childCount,spanCount)){

                    return true;

                }else{

                    return false;
                }
            }
        }
        return false;
    }

    private int getColumNum(int pos,int spanCount){

        return getColum(pos+1,spanCount);

    }
    private int getColum(int childCount,int spanCount){

        int columNum = childCount / spanCount + ((childCount % spanCount > 0) ? 1:0);
        return columNum;
    }

    private int getRawNum(int pos,int spanCount){

        return getRaw(pos+1,spanCount);

    }

    private int getRaw(int childCount,int spanCount){

       int rawNum = childCount / spanCount + ((childCount % spanCount > 0) ? 1:0);
       return rawNum;

    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
               if(getRawNum(pos,spanCount)  == getRaw(childCount,spanCount)){

                   return true;

               }else{

                   return false;
               }

        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if(getRawNum(pos,spanCount)  == getRaw(childCount,spanCount)){

                    return true;

                }else{

                    return false;
                }
            } else {
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
        {
            outRect.set(0, 0, mHorizontalDivider.getIntrinsicWidth(), 0);

        } else
        if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
        {
            outRect.set(0, 0, 0, mVerticalDivider.getIntrinsicHeight());

        } else {

            outRect.set(0, 0, mHorizontalDivider.getIntrinsicWidth(),
                    mVerticalDivider.getIntrinsicHeight());
        }
    }
}
