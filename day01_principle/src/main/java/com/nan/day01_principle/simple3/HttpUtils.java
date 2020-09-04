package com.nan.day01_principle.simple3;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
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

    private static OkHttpClient sOkHttpClient = new OkHttpClient();
    private static Gson sGson = new Gson();

    private HttpUtils() {
    }

    public static <T> void get(Context context, String url, Map<String, Object> params, final HttpCallBack<T> callBack, final boolean cache) {
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

        final String realUrl = Utils.jointParams(url, params);
        Log.d(TAG, String.format("Post请求路径：%s", url));

        final String cacheJson = (String) PreferencesUtil.getInstance().getParam(realUrl, "");
        // 写一大堆处理逻辑 ，内存怎么扩展等等
        if (cache && !TextUtils.isEmpty(cacheJson)) {
            T objResult = (T) sGson.fromJson(cacheJson, Utils.analysisClazzInfo(callBack));
            callBack.onSuccess(objResult);
        }

        Request request = new Request.Builder()
                .url(url)
                .get()
                .tag(context)
                .build();

        sOkHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        callBack.onFailure(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                        T result = (T) sGson.fromJson(json, Utils.analysisClazzInfo(callBack));
                        callBack.onSuccess(result);

                        if (cache) {
                            PreferencesUtil.getInstance().saveParam(realUrl, json);
                        }
                    }
                });
    }

}
