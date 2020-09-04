package com.nan.day01_principle.simple5.http;

import android.content.Context;
import android.text.TextUtils;

import com.nan.day01_principle.simple5.http.cache.IHttpCache;
import com.nan.day01_principle.simple5.http.request.IHttpRequest;
import com.nan.day01_principle.simple5.http.request.OkHttpRequest;

import java.util.HashMap;
import java.util.Map;

public class HttpHelper {

    private static final String TAG = "HttpUtils";
    private IHttpRequest mHttpRequest;
    private static IHttpRequest sInitHttpRequest;
    private int mType = TYPE_GET;
    private static final int TYPE_GET = 0;
    private static final int TYPE_POST = 1;
    private Map<String, Object> mParams;
    private boolean mCache = false;
    private Context mContext;
    private String mUrl;

    public static void init(IHttpRequest httpRequest) {
        sInitHttpRequest = httpRequest;
    }

    private HttpHelper(Context context) {
        mContext = context;
        mHttpRequest = OkHttpRequest.getInstance();
        mParams = new HashMap<>();
    }

    public static HttpHelper with(Context context) {
        return new HttpHelper(context);
    }

    public HttpHelper http(IHttpRequest httpRequest) {
        mHttpRequest = httpRequest;
        return this;
    }

    public HttpHelper url(String url) {
        mUrl = url;
        return this;
    }

    public HttpHelper get() {
        mType = TYPE_GET;
        return this;
    }

    public HttpHelper post() {
        mType = TYPE_POST;
        return this;
    }

    public HttpHelper param(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public HttpHelper cache(boolean cache) {
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

        IHttpRequest executeHttpRequest = mHttpRequest;
        if (null == mHttpRequest) {
            executeHttpRequest = sInitHttpRequest;
        }

        if (mType == TYPE_GET) {
            executeHttpRequest.get(mContext, mUrl, mParams, callBack, mCache);
        } else if (mType == TYPE_POST) {
            executeHttpRequest.post(mContext, mUrl, mParams, callBack, mCache);
        }
    }

}
