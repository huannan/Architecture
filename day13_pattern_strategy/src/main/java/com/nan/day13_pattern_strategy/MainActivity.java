package com.nan.day13_pattern_strategy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 默认使用当前类名为TAG
        // Timber.tag("MainActivity");
        Timber.d("Hello %s!", "huannan");
    }
}
