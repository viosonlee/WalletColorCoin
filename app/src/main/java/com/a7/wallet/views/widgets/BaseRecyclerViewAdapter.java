package com.a7.wallet.views.widgets;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:李烽
 * Date:2016-05-04
 * FIXME
 * Todo 通用的adapter
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    private static final String TAG = "BaseRecyclerViewAdapter";
    protected List<T> mData;
    protected Context mContext;
    protected LayoutInflater mInflater;

    protected RecyclerView mRecyclerView;
    protected View VIEW_FOOTER;
    protected View VIEW_HEADER;

    //Type
    protected int TYPE_NORMAL = 1000;
    protected int TYPE_HEADER = 1001;
    protected int TYPE_FOOTER = 1002;

    public BaseRecyclerViewAdapter(Context context, List<T> list) {
        mData = (list != null) ? list : new ArrayList<T>();
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount(List data) {

        if (haveFooterView() && haveHeaderView())
            return data == null ? 2 : data.size() + 2;
        else if ((haveFooterView() && !haveHeaderView()) || (!haveFooterView() && haveHeaderView()))
            return data == null ? 1 : data.size() + 1;
        else {
            return data == null ? 0 : data.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }

    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }

    public void refreshHeaderView(View headerView) {
        if (haveHeaderView()) {
            notifyItemRemoved(0);
            ViewGroup.LayoutParams params =
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        } else addHeaderView(headerView);
    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }


    protected boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    protected boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

    public View getHeaderView() {
        return VIEW_HEADER;
    }

    public View getFooterView() {
        return VIEW_FOOTER;
    }

    public boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            RecyclerViewHolder headerViewHolder = new RecyclerViewHolder(mContext, VIEW_HEADER);
            return headerViewHolder;
        } else if (viewType == TYPE_FOOTER) {
            RecyclerViewHolder footerViewHolder = new RecyclerViewHolder(mContext, VIEW_FOOTER);
            return footerViewHolder;
        } else {
            View view = mInflater.inflate(getItemLayout(viewType), parent, false);
            final RecyclerViewHolder holder
                    = new RecyclerViewHolder(mContext, view);
            if (onItemClickListener == null) {

                onItemClickListener = getOnItemClickListener();
            }
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(
                        v -> onItemClickListener.onItemClick(holder.itemView, getRealPosition(holder))
                );
            }
            if (onItemLongClickListener == null) {

                onItemLongClickListener = getOnItemLongClickListener();
            }
            if (onItemLongClickListener != null)
                holder.itemView.setOnLongClickListener(
                        v -> {
                            onItemLongClickListener.onItemLongClick(holder.itemView, getRealPosition(holder));
                            return true;
                        }
                );

            return holder;
        }
    }

    public int getRealPosition(RecyclerViewHolder holder) {
        if (haveHeaderView())
            return holder.getLayoutPosition() - 1;
        else return holder.getLayoutPosition();
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == TYPE_HEADER) {
        } else if (itemViewType == TYPE_FOOTER) {
        } else if (itemViewType == TYPE_NORMAL) {
            if (haveHeaderView())
                position--;
            bindData(holder, position, mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return getCount(mData);
    }

    public void add(int position, T item) {
        mData.add(position, item);
        notifyItemInserted(position);
    }

    public void delete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    protected abstract void bindData(RecyclerViewHolder holder, int position, T t);

    protected abstract int getItemLayout(int viewType);

    private OnItemClickListener onItemClickListener;

    protected OnItemClickListener getOnItemClickListener() {

        return null;
    }

    protected OnItemLongClickListener getOnItemLongClickListener() {

        return null;
    }

    private OnItemLongClickListener onItemLongClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
