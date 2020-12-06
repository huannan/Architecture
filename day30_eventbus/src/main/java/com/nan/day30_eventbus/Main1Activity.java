package com.nan.day30_eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.nan.day30_eventbus.eventbus.EventBus;
import com.nan.day30_eventbus.eventbus.Subscribe;
import com.nan.day30_eventbus.eventbus.ThreadMode;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = "Main1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100, sticky = false)
    public void test1(String event) {
        Log.e(TAG, "test1: " + event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 50, sticky = false)
    public void test2(String event) {
        Log.e(TAG, "test2: " + event);
    }

    public void toMain2Activity(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
