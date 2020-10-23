package com.nan.day14_pattern_adapter.simple4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nan.day14_pattern_adapter.R;

import java.util.List;

public class SimpleAdapter extends BaseAdapter {

    private List<String> mData;

    public SimpleAdapter(List<String> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public View getView(int position, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText(mData.get(position));
        return view;
    }
}
