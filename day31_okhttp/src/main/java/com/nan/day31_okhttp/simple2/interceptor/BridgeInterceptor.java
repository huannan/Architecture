package com.nan.day31_okhttp.simple2.interceptor;

import android.util.Log;

import com.nan.day31_okhttp.simple2.Request;
import com.nan.day31_okhttp.simple2.RequestBody;
import com.nan.day31_okhttp.simple2.Response;

import java.io.IOException;

public class BridgeInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.e("TAG", "BridgeInterceptor");
        Request request = chain.request();
        // 添加一些请求头
        request.addHeader("Connection", "keep-alive");
        // 做一些其他处理
        if (request.body() != null) {
            RequestBody requestBody = request.body();
            request.addHeader("Content-Type", requestBody.getContentType());
            request.addHeader("Content-Length", Long.toString(requestBody.getContentLength()));
        }
        Response response = chain.proceed(request);

        return response;
    }
}
