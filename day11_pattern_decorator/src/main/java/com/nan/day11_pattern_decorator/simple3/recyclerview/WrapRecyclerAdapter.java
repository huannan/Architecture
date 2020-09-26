package com.nan.day11_pattern_decorator.simple3.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 装饰后的Adapter,对原来的Adapter进行功能扩展,支持头部和尾部的添加
 */
public class WrapRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 原来的Adapter,并不支持头部和尾部的添加
    private RecyclerView.Adapter mRealAdapter;

    private List<View> mHeaderViews;
    private List<View> mFooterViews;

    public WrapRecyclerAdapter(RecyclerView.Adapter<?> realAdapter) {
        mRealAdapter = realAdapter;
        mRealAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                notifyDataSetChanged();
            }
        });
        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int position = viewType;
        int headersCount = getHeadersCount();
        // 头部的ViewHolder
        if (position < headersCount) {
            return createHeaderFooterViewHolder(mHeaderViews.get(position));
        }
        // 真实的ViewHolder
        int adjPosition = position - headersCount;
        int realAdapterCount = mRealAdapter.getItemCount();
        if (mRealAdapter != null) {
            if (adjPosition < realAdapterCount) {
                // 直接传position,那么会使得原来的viewType出问题
                return mRealAdapter.onCreateViewHolder(viewGroup, mRealAdapter.getItemViewType(adjPosition));
            }
        }
        // 尾部的ViewHolder
        return createHeaderFooterViewHolder(mFooterViews.get(adjPosition - realAdapterCount));
    }

    private RecyclerView.ViewHolder createHeaderFooterViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        // 只有真实数据需要处理
        int headersCount = getHeadersCount();
        if (position < headersCount) {
            return;
        }
        int adjPosition = position - headersCount;
        int realAdapterCount = mRealAdapter.getItemCount();
        if (mRealAdapter != null) {
            if (adjPosition < realAdapterCount) {
                // 直接传position,那么会使得原来的viewType出问题
                mRealAdapter.onBindViewHolder(viewHolder, adjPosition);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        // 直接把位置作为viewType
        return position;
    }

    @Override
    public int getItemCount() {
        int realAdapterCount = null != mRealAdapter ? mRealAdapter.getItemCount() : 0;
        return getHeadersCount() + getFootersCount() + realAdapterCount;
    }

    protected void addHeaderView(View view) {
        if (mHeaderViews.contains(view)) {
            throw new RuntimeException("同一个头部不可以重复添加");
        }
        mHeaderViews.add(view);
        notifyDataSetChanged();
    }

    protected void removeHeaderView(View view) {
        if (mHeaderViews.contains(view)) {
            mHeaderViews.remove(view);
            notifyDataSetChanged();
        }
    }

    protected void addFooterView(View view) {
        if (mFooterViews.contains(view)) {
            throw new RuntimeException("同一个尾部不可以重复添加");
        }
        mFooterViews.add(view);
        notifyDataSetChanged();
    }

    protected void removeFooterView(View view) {
        if (mFooterViews.contains(view)) {
            mFooterViews.remove(view);
            notifyDataSetChanged();
        }
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFooterViews.size();
    }
}
