package com.nan.day09_pattern_builder.navigation;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * 导航栏的基类
 */
public class AbsNavigationBar implements INavigation {

    protected Builder mBuilder;
    private View mNavigationBar;

    protected AbsNavigationBar(Builder builder) {
        mBuilder = builder;
        View navigationBar = createNavigationBar(mBuilder.mContext, mBuilder.mLayout, builder.mParent);
        attachToParent(navigationBar, mBuilder.mParent);
        attachParams();
    }

    @Override
    public View createNavigationBar(Context context, int layout, ViewGroup parent) {
        mNavigationBar = LayoutInflater.from(context).inflate(layout, parent, false);
        return mNavigationBar;
    }

    @Override
    public void attachToParent(View navigationBar, ViewGroup parent) {
        parent.addView(navigationBar);
    }

    @Override
    public void attachParams() {
        for (Map.Entry<Integer, String> entry : mBuilder.mTextMap.entrySet()) {
            View view = findViewById(entry.getKey());
            if (view instanceof TextView) {
                ((TextView) view).setText(entry.getValue());
            }
        }

        for (Map.Entry<Integer, View.OnClickListener> entry : mBuilder.mListenerMap.entrySet()) {
            View view = findViewById(entry.getKey());
            view.setOnClickListener(entry.getValue());
        }
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return mNavigationBar.findViewById(id);
    }

    /**
     * 构建类，用于构建NavigationBar，以及存储参数
     */
    public static abstract class Builder {

        protected Context mContext;
        protected int mLayout;
        protected ViewGroup mParent;
        protected Map<Integer, String> mTextMap;
        protected Map<Integer, View.OnClickListener> mListenerMap;

        public Builder(Context context, int layoutId, ViewGroup parent) {
            mContext = context;
            mLayout = layoutId;
            mParent = parent;
            mTextMap = new HashMap<>();
            mListenerMap = new HashMap<>();
        }

        /**
         * 用于创建NavigationBar
         */
        public abstract AbsNavigationBar build();

        public Builder setText(int viewId, String text) {
            mTextMap.put(viewId, text);
            return this;
        }

        public Builder setListener(int viewId, View.OnClickListener listener) {
            mListenerMap.put(viewId, listener);
            return this;
        }
    }

}
