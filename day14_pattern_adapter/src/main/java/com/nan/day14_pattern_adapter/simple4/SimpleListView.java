package com.nan.day14_pattern_adapter.simple4;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * 简单的ListView，不考虑复用等问题
 */
public class SimpleListView extends ScrollView {

    private LinearLayout mContainer;
    private BaseAdapter mAdapter;

    public SimpleListView(Context context) {
        this(context, null);
    }

    public SimpleListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContainer = new LinearLayout(context);
        mContainer.setOrientation(LinearLayout.VERTICAL);
        addView(mContainer, 0);
    }

    @Override
    public void addView(View child) {
        mContainer.addView(child);
    }

    /**
     * 设置适配器
     * 省略观察者的注册反注册
     */
    public void setAdapter(BaseAdapter adapter) {
        mAdapter = adapter;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View view = mAdapter.getView(i, mContainer);
            mContainer.addView(view);
        }
    }
}
