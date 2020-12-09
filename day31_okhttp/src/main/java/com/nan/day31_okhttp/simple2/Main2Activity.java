package com.nan.day31_okhttp.simple2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nan.day31_okhttp.R;

import java.io.IOException;

/**
 * OkHttp源码架构基本实现
 */
public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .get()
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                if (null == body) {
                    return;
                }
                Log.e(TAG, String.format("返回状态码：%d", response.code()));
                Log.e(TAG, String.format("返回结果：%s", body.string()));
            }
        });
    }
}
