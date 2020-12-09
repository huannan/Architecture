package com.nan.day31_okhttp.simple4;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.nan.day31_okhttp.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;

import io.reactivex.functions.Consumer;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * OkHttp自定义缓存
 * 自定义缓存（要求：有网 30s 内请求读缓存，无网直接读缓存）
 * OkHttp 自带的扩展有坑，我们之前自己写过这个缓存管理，与 OkHttp 结合就可以了
 * 具体的坑可以参考：https://blog.csdn.net/jhg1204/article/details/51397689
 */
public class Main4Activity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mTv;
    private OkHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            // 权限申请，并且用户给了权限
                            request();
                        }
                    }
                });
    }

    private void request() {
        if (null == mClient) {
            Cache cache = new Cache(getExternalCacheDir(), 10 * 1024 * 1024);
            mClient = new OkHttpClient.Builder()
                    .cache(cache)
                    // 无网络的情况下只读缓存
                     .addInterceptor(new CacheRequestInterceptor())
                    // 自定义缓存,30秒内有效
                    .addNetworkInterceptor(new CacheResponseInterceptor())
                    .build();
        }

        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .get()
                .build();
        Call call = mClient.newCall(request);

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
                Log.e(TAG, String.format("返回结果：%s", response.cacheResponse() + "\n" + response.networkResponse()));
            }
        });
    }

}
