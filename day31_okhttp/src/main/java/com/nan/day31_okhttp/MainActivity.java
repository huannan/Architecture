package com.nan.day31_okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient client = new OkHttpClient();

        // 1. 构建一个请求，url、端口、请求头等一些参数
        // 内部添加处理了很多参数，例如表单提交，内部已经帮我们添加了content-type、content-length等
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .get()
                .build();

        // 2. 把Request封装转成一个RealCall
        Call call = client.newCall(request);

        // 3. 加入到线程池里面执行（重点分析）
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
