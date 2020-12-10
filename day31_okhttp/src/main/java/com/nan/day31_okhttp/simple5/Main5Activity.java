package com.nan.day31_okhttp.simple5;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.nan.day31_okhttp.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp文件下载简单示例
 */
public class Main5Activity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mTv;
    private OkHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
        mClient = new OkHttpClient();

        // rxPermission
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            // 权限申请，并且用户给了权限
                            // downloadFileSingleThread();
                            downloadFileMultiThread();
                        }
                    }
                });
    }

    /**
     * 单线程下载
     */
    private void downloadFileSingleThread() {
        Log.e(TAG, "开始下载文件");

        Request request = new Request.Builder()
                .url("https://d.toutiao.com/PqXU?apk=1")
                .build();

        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "请求失败：" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, String.format("返回状态码：%d", response.code()));

                long total = response.body().contentLength();
                long current = 0L;

                File file = new File(getExternalFilesDir("apk"), "toutiao.apk");
                FileOutputStream outputStream = new FileOutputStream(file);

                InputStream inputStream = response.body().byteStream();
                byte[] buffer = new byte[10 * 1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                    current += len;
                    Log.e(TAG, "进度: " + (int) (current * 100 / total));
                }

                inputStream.close();
                outputStream.close();
            }
        });
    }

    /**
     * 多线程下载
     * 待完善：下载回调（注意多线程问题）+使用线程池+下载状态管理+等
     */
    private void downloadFileMultiThread() {
        Log.e(TAG, "开始下载文件");

        final String url = "https://d.toutiao.com/PqXU?apk=1";

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "请求失败：" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, String.format("返回状态码：%d", response.code()));

                // 总长度
                final long total = response.body().contentLength();
                // 线程数
                int threadCount = 3;
                // 每个线程下载的大小
                long threadSize = total / threadCount;

                for (int i = 0; i < threadCount; i++) {
                    final long start = i * threadSize;
                    long end = start + threadSize - 1;
                    if (i == threadCount - 1) {
                        end = total - 1;
                    }

                    Log.e(TAG, "onResponse: start=" + start + " end=" + end);

                    Request request = new Request.Builder()
                            .url(url)
                            .addHeader("Range", "bytes=" + start + "-" + end)
                            .build();

                    mClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(TAG, "请求失败：" + e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            InputStream inputStream = response.body().byteStream();

                            File apk = new File(getExternalFilesDir("apk"), "toutiao.apk");
                            RandomAccessFile file = new RandomAccessFile(apk, "rwd");
                            file.seek(start);

                            int len;
                            byte[] buffer = new byte[1024 * 10];
                            while ((len = inputStream.read(buffer)) != -1) {
                                // 保存进度，做断点
                                file.write(buffer, 0, len);
                            }

                            inputStream.close();
                            file.close();
                        }
                    });
                }
            }
        });
    }

}
