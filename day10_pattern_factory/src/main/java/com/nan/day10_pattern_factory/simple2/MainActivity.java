package com.nan.day10_pattern_factory.simple2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.nan.day10_pattern_factory.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
        mTv.setOnClickListener(this);

        // 问题分析：
        // 清理缓存的功能，某些需要清理，某些不需要清理；或者为了保证性能，采取内存/数据库等其他方式存储
        PreferencesUtils.getInstance()
                .putString("name", "huannan1")
                .putInt("age", 28)
                .commit();
    }

    @Override
    public void onClick(View v) {
        String name = PreferencesUtils.getInstance().getString("name", "");
        int age = PreferencesUtils.getInstance().getInt("age", 0);
        mTv.setText(name + " " + age);
    }
}
