package com.nan.day14_pattern_adapter.simple4;

import android.view.View;
import android.view.ViewGroup;

/**
 * 将后台返回的字符串列表适配成View
 */
public abstract class BaseAdapter {

    public abstract int getCount();

    public abstract View getView(int position, ViewGroup parent);

}
