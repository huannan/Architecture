package com.nan.day21_pattern_flyweight;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Message.obtain();
        LayoutInflater.from(this).inflate(-1, null);
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(null);
    }
}
