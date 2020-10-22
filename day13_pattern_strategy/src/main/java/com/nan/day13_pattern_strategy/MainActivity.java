package com.nan.day13_pattern_strategy;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 自己实现一个可以这样做：通过线程不断地去改变属性
        ImageView imageView = null;
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "", 1.0F);
        animator.setDuration(400L);
        animator.start();

        // 默认使用当前类名为TAG
        // Timber.tag("MainActivity");
        Timber.d("Hello %s!", "huannan");
    }
}
