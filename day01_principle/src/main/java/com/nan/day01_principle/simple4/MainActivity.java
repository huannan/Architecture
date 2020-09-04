package com.nan.day01_principle.simple4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nan.day01_principle.ConstantValue;
import com.nan.day01_principle.R;
import com.nan.day01_principle.simple4.http.HttpCallBack;
import com.nan.day01_principle.simple4.http.HttpUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        HttpUtils.with(this)
                .url(ConstantValue.UrlConstant.BASE_URL)
                .get()
                .param("code", "utf-8")
                .param("q", "衣服")
                .cache(true)
                .request(new HttpCallBack<Object>() {
                    @Override
                    public void onSuccess(Object result) {

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });

    }
}