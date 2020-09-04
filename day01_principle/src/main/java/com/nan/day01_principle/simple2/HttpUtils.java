package com.nan.day01_principle.simple2;

import android.content.Context;
import android.util.Log;

import com.nan.day01_principle.ConstantValue;
import com.nan.day01_principle.Utils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {

    private static final String TAG = "HttpUtils";

    private static OkHttpClient sOkHttpClient;

    private HttpUtils() {
    }

    public static void get(Context context, String url, Map<String, Object> params, final Callback callback) {
        if (null == sOkHttpClient) {
            sOkHttpClient = new OkHttpClient();
        }

        // 公共参数
        params.put("app_name", "joke_essay");
        params.put("version_name", "5.7.0");
        params.put("ac", "wifi");
        params.put("device_id", "30036118478");
        params.put("device_brand", "Xiaomi");
        params.put("update_version_code", "5701");
        params.put("manifest_version_code", "570");
        params.put("longitude", "113.000366");
        params.put("latitude", "28.171377");
        params.put("device_platform", "android");

        url = Utils.jointParams(ConstantValue.UrlConstant.BASE_URL, params);
        Log.d(TAG, String.format("Post请求路径：%s", url));

        Request request = new Request.Builder()
                .url(url)
                .get()
                .tag(context)
                .build();

        sOkHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "请求失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                        Log.d(TAG, String.format("返回结果：%s", json));
                        // 1.JSON解析转换
                        // 2.显示列表数据
                        // 3.缓存数据
                    }
                });
    }

}
