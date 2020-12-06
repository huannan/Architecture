package com.nan.day30_eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nan.day30_eventbus.eventbus.EventBus;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void returnToMain1Activity(View view) {
        EventBus.getDefault().post("我爱你");
        finish();
    }
}