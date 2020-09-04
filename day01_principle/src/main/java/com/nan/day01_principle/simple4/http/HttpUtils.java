package com.nan.day01_principle.simple4.http;

import android.content.Context;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    private static final String TAG = "HttpUtils";
    private OkHttpRequest mHttpRequest;
    private int mType = TYPE_GET;
    private static final int TYPE_GET = 0;
    private static final int TYPE_POST = 1;
    private Map<String, Object> mParams;
    private boolean mCache = false;
    private Context mContext;
    private String mUrl;

    private HttpUtils(Context context) {
        mContext = context;
        mHttpRequest = new OkHttpRequest();
        mParams = new HashMap<>();
    }

    public static HttpUtils with(Context context) {
        return new HttpUtils(context);
    }

    public HttpUtils url(String url) {
        mUrl = url;
        return this;
    }

    public HttpUtils get() {
        mType = TYPE_GET;
        return this;
    }

    public HttpUtils post() {
        mType = TYPE_POST;
        return this;
    }

    public HttpUtils param(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public HttpUtils cache(boolean cache) {
        mCache = cache;
        return this;
    }

    public void request() {
        request(null);
    }

    public <T> void request(HttpCallBack<T> callBack) {
        if (TextUtils.isEmpty(mUrl)) {
            throw new RuntimeException("url empty");
        }
        if (mType == TYPE_GET) {
            mHttpRequest.get(mContext, mUrl, mParams, callBack, mCache);
        } else if (mType == TYPE_POST) {
            mHttpRequest.post(mContext, mUrl, mParams, callBack, mCache);
        }
    }

}
