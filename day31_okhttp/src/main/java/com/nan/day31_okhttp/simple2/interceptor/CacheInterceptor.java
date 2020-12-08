package com.nan.day31_okhttp.simple2.interceptor;

import com.nan.day31_okhttp.simple2.Request;
import com.nan.day31_okhttp.simple2.Response;

import java.io.IOException;

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        // 本地有没有缓存，如果有没过期
        /*if(true){
            return new Response(new );
        }*/
        return chain.proceed(request);
    }
}
