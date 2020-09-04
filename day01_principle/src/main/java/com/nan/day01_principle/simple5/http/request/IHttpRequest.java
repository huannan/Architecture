package com.nan.day01_principle.simple5.http.request;

import android.content.Context;

import com.nan.day01_principle.simple5.http.HttpCallBack;

import java.util.Map;

public interface IHttpRequest {
    <T> void get(Context context, String url, Map<String, Object> params, final HttpCallBack<T> callBack, final boolean cache);

    <T> void post(Context context, String url, Map<String, Object> params, final HttpCallBack<T> callBack, final boolean cache);
}
