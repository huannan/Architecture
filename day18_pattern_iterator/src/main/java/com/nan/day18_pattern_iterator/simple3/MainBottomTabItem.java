package com.nan.day18_pattern_iterator.simple3;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nan.day18_pattern_iterator.R;
import com.nan.day18_pattern_iterator.simple3.bottomtab.BottomTabItem;

/**
 * 这个要自己写
 */
public class MainBottomTabItem extends BottomTabItem {
    private Builder builder;

    private MainBottomTabItem(Context context) {
        super(R.layout.tab_main_bottom_item, context);
    }

    public MainBottomTabItem(Builder builder) {
        this(builder.context);
        this.builder = builder;
    }

    @Override
    protected void initLayout() {
        // 初始化显示
        TextView tabText = findViewById(R.id.tab_text);
        ImageView tabIcon = findViewById(R.id.tab_icon);

        if (!TextUtils.isEmpty(builder.text)) {
            tabText.setText(builder.text);
        }

        if (builder.resIconId != 0) {
            tabIcon.setImageResource(builder.resIconId);
        }
    }

    @Override
    protected void setSelected(boolean selected) {
        TextView tabText = findViewById(R.id.tab_text);
        ImageView tabIcon = findViewById(R.id.tab_icon);

        tabText.setSelected(selected);
        tabIcon.setSelected(selected);
    }


    public static class Builder {
        Context context;
        String text;
        int resIconId;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder resIcon(int resIconId) {
            this.resIconId = resIconId;
            return this;
        }

        public MainBottomTabItem create() {
            return new MainBottomTabItem(this);
        }
    }
}
