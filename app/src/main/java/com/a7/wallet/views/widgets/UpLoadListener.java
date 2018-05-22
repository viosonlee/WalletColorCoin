package com.a7.wallet.views.widgets;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


/**
 * Author:李烽
 * Date:2016-09-02
 * FIXME
 * Todo 上拉加载更多
 */
public abstract class UpLoadListener extends RecyclerView.OnScrollListener {
    private RecyclerView.LayoutManager layoutManager;
    private int childCount;
    private int itemCount;
    private int firstVisibleItemPosition;

    public UpLoadListener(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        childCount = recyclerView.getChildCount();//显示的数量
        itemCount = layoutManager.getItemCount();//总数量
        if (layoutManager instanceof LinearLayoutManager)
            firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        else if (layoutManager instanceof GridLayoutManager)
            firstVisibleItemPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//        DebugLog.i(getClass().getSimpleName(), "newState:" + newState +
//                "\nchildCount:" + childCount + "\nitemCount:" + itemCount +
//                "\nfirstVisibleItemPosition:" + firstVisibleItemPosition);
        if (newState == RecyclerView.SCROLL_STATE_IDLE && ((itemCount - childCount) <= firstVisibleItemPosition)) {
            onLoadMore();
        }
    }

    protected abstract void onLoadMore();
}
