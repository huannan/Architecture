package com.nan.day01_principle.simple5.http.request;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.nan.day01_principle.Utils;
import com.nan.day01_principle.simple5.http.HttpCallBack;
import com.nan.day01_principle.simple5.http.cache.SPHttpCache;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

public class XUtilsRequest implements IHttpRequest {

    private static final String TAG = "XUtilsRequest";
    private static XUtilsRequest sInstance;
    private Gson mGson;
    private SPHttpCache mHttpCache;

    private XUtilsRequest() {
        mGson = new Gson();
        mHttpCache = new SPHttpCache();
    }

    public static XUtilsRequest getInstance() {
        if (null == sInstance) {
            synchronized (OkHttpRequest.class) {
                if (null == sInstance) {
                    sInstance = new XUtilsRequest();
                }
            }
        }
        return sInstance;
    }

    @Override
    public <T> void get(Context context, String url, Map<String, Object> params, final HttpCallBack<T> callBack, final boolean cache) {
        RequestParams requestParams = new RequestParams();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            requestParams.addParameter(entry.getKey(), entry.getValue());
        }

        final String realUrl = Utils.jointParams(url, params);
        Log.d(TAG, String.format("Post请求路径：%s", url));

        String cacheJson = mHttpCache.readCache(realUrl);
        // 写一大堆处理逻辑 ，内存怎么扩展等等
        if (cache && !TextUtils.isEmpty(cacheJson)) {
            T objResult = (T) mGson.fromJson(cacheJson, Utils.analysisClazzInfo(callBack));
            callBack.onSuccess(objResult);
            return;
        }

        x.http().get(requestParams, new Callback.CommonCallback<T>() {
            @Override
            public void onSuccess(T result) {
                callBack.onSuccess(result);

                if (cache) {
                    mHttpCache.saveCache(realUrl, mGson.toJson(realUrl));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onFailure(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public <T> void post(Context context, String url, Map<String, Object> params, final HttpCallBack<T> callBack, final boolean cache) {

    }
}
