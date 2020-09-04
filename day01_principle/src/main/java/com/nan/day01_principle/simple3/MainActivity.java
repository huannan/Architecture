package com.nan.day01_principle.simple3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nan.day01_principle.ConstantValue;
import com.nan.day01_principle.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        Map<String, Object> params = new HashMap<>();
        // 特定参数
        params.put("code", "utf-8");
        params.put("q", "衣服");

        HttpUtils.get(this, ConstantValue.UrlConstant.BASE_URL, params, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        }, true);
    }
}