package com.nan.day09_pattern_builder.navigation;

import android.content.Context;
import android.view.ViewGroup;

/**
 * 可以自定义ViewLayout
 */
public class NavigationBar extends AbsNavigationBar{

    protected NavigationBar(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbsNavigationBar.Builder{

        public Builder(Context context, int layoutId, ViewGroup parent) {
            super(context, layoutId, parent);
        }

        @Override
        public NavigationBar build() {
            return new NavigationBar(this);
        }
    }
}
