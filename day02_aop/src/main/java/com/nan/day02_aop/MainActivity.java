package com.nan.day02_aop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nan.day02_aop.aop.CheckNet;
import com.nan.day02_aop.util.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jump1(View view) {
        if (Utils.isNetworkAvailable(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @CheckNet
    public void jump2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}