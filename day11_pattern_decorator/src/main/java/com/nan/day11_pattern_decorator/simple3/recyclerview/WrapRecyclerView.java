package com.nan.day11_pattern_decorator.simple3.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class WrapRecyclerView extends RecyclerView {

    private WrapRecyclerAdapter mAdapter;

    public WrapRecyclerView(@NonNull Context context) {
        super(context);
    }

    public WrapRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        mAdapter = new WrapRecyclerAdapter(adapter);
        super.setAdapter(mAdapter);
    }

    public void addHeaderView(View view) {
        if (null == mAdapter) {
            throw new RuntimeException("添加头部之前请先setAdapter");
        }
        mAdapter.addHeaderView(view);
    }

    public void removeHeaderView(View view) {
        if (null == mAdapter) {
            throw new RuntimeException("移除头部之前请先setAdapter");
        }
        mAdapter.removeHeaderView(view);
    }

    public void addFooterView(View view) {
        if (null == mAdapter) {
            throw new RuntimeException("添加尾部之前请先setAdapter");
        }
        mAdapter.addFooterView(view);
    }

    public void removeFooterView(View view) {
        if (null == mAdapter) {
            throw new RuntimeException("移除尾部之前请先setAdapter");
        }
        mAdapter.removeFooterView(view);
    }
}
