package com.nan.day03_uml;

import android.os.Bundle;

public class MainActivity extends BaseSkinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MainActivity依赖HttpUtils、ButterKnife
        // HttpUtils.with().post.execute();
        // ButterKnife.inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void changeSkin() {

    }
}
