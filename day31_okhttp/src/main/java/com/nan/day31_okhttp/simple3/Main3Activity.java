package com.nan.day31_okhttp.simple3;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.nan.day31_okhttp.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;

import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * OkHttp文件上传进度监听
 */
public class Main3Activity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mTv;
    private OkHttpClient mClient;
    private Runnable mUpdateUiRunnable;
    private int mUploadProgress;

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
                            uploadFile();
                        }
                    }
                });
    }

    private void uploadFile() {
        File dir = getExternalFilesDir("apk");
        File file = new File(dir.getAbsolutePath(), "test.apk");
        if (!file.isFile() || !file.exists()) {
            Toast.makeText(this, "请将test.apk复制到" + dir.getAbsolutePath(), Toast.LENGTH_LONG).show();
            return;
        }

        Log.e(TAG, "开始上传文件");

        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("os", "android")
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse(guessMimeType(file.getAbsolutePath())), file))
                .build();

        ExMultipartBody exMultipartBody = new ExMultipartBody(body, new UploadProgressListener() {
            @Override
            public void onProgress(long total, long current, int progress) {
                mUploadProgress = progress;
                if (null == mUpdateUiRunnable) {
                    mUpdateUiRunnable = new Runnable() {
                        @Override
                        public void run() {
                            mTv.setText(mUploadProgress + "");
                        }
                    };
                }
                runOnUiThread(mUpdateUiRunnable);
            }
        });

        Request request = new Request.Builder()
                .url("http://172.16.47.80:8080/TestServer/upload")
                .post(exMultipartBody)
                .build();

        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "请求失败：" + e.getMessage());
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

    private String guessMimeType(String filePath) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String mimType = fileNameMap.getContentTypeFor(filePath);
        if (TextUtils.isEmpty(mimType)) {
            return "application/octet-stream";
        }
        return mimType;
    }
}
