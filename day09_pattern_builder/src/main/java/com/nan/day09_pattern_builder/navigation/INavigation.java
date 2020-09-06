package com.nan.day09_pattern_builder.navigation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public interface INavigation {
    View createNavigationBar(Context context, int layout, ViewGroup parent);

    void attachToParent(View navigationBar, ViewGroup parent);

    void attachParams();
}
