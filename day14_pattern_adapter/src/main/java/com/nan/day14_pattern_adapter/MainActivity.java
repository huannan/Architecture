package com.nan.day14_pattern_adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.nan.day14_pattern_adapter.simple4.SimpleAdapter;
import com.nan.day14_pattern_adapter.simple4.SimpleListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SimpleListView mListView;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.list);

        mData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mData.add("列表项 " + i);
        }

        // test1();
        test2();
    }

    /**
     * 一般写法
     */
    private void test1() {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < mData.size(); i++) {
            View view = inflater.inflate(R.layout.item_text, mListView, false);
            TextView tvTitle = view.findViewById(R.id.tv_title);
            tvTitle.setText(mData.get(i));
            mListView.addView(view);
        }
    }

    /**
     * 进阶写法，通过适配器把数据适配成View
     */
    private void test2() {
        mListView.setAdapter(new SimpleAdapter(mData));
    }
}
