package com.nan.day31_okhttp.simple4;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class CacheResponseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        // 过期时间是30秒
        response = response.newBuilder()
                .removeHeader("Cache-Control")
                .removeHeader("Pragma")
                .addHeader("Cache-Control", "max-age=30")
                .build();
        return response;
    }
}
