package com.nan.day09_pattern_builder.navigation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.nan.day09_pattern_builder.R;

/**
 * 默认样式
 */
public class DefaultNavigationBar extends AbsNavigationBar {

    protected DefaultNavigationBar(Builder builder) {
        super(builder);
    }

    @Override
    public void attachParams() {
        super.attachParams();

        // 处理特有的参数
        Builder builder = (Builder) mBuilder;
        findViewById(R.id.tv_left).setVisibility(builder.mLeftTextVisible);
    }

    public static class Builder extends AbsNavigationBar.Builder {

        public int mLeftTextVisible = View.VISIBLE;

        public Builder(Context context, ViewGroup parent) {
            super(context, R.layout.view_navigation_bar, parent);
        }

        @Override
        public DefaultNavigationBar build() {
            return new DefaultNavigationBar(this);
        }

        public Builder setLeftText(String text, View.OnClickListener listener) {
            setText(R.id.tv_left, text);
            setListener(R.id.tv_left, listener);
            return this;
        }

        public Builder setLeftTextVisible(int visible) {
            mLeftTextVisible = visible;
            return this;
        }
    }
}
