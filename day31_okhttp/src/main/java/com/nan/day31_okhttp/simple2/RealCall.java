package com.nan.day31_okhttp.simple2;

import android.util.Log;

import com.nan.day31_okhttp.simple2.interceptor.BridgeInterceptor;
import com.nan.day31_okhttp.simple2.interceptor.CacheInterceptor;
import com.nan.day31_okhttp.simple2.interceptor.CallServerInterceptor;
import com.nan.day31_okhttp.simple2.interceptor.Interceptor;
import com.nan.day31_okhttp.simple2.interceptor.RealInterceptorChain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RealCall implements Call {

    private final OkHttpClient client;
    private final Request originalRequest;

    public RealCall(OkHttpClient client, Request request) {
        this.client = client;
        this.originalRequest = request;
    }

    public static Call newCall(OkHttpClient client, Request request) {
        return new RealCall(client, request);
    }

    @Override
    public Response execute() throws IOException {
        return null;
    }

    @Override
    public void enqueue(Callback responseCallback) {
        client.dispatcher.enqueue(new AsyncCall(responseCallback));
    }

    @Override
    public void cancel() {

    }

    final class AsyncCall extends NameRunnable {

        private final Callback callback;

        public AsyncCall(Callback responseCallback) {
            this.callback = responseCallback;
        }

        /**
         * 基于HttpUrlConnection，而OkHttp是Socket+OkIO
         */
        @Override
        public void execute() {
            // 来这里，开始访问网络 Request -> Response
            Log.e(OkHttpClient.TAG, "开始网络请求");
            try {
                Response response = getResponseWithInterceptorChain();
                callback.onResponse(RealCall.this, response);
            } catch (IOException e) {
                callback.onFailure(RealCall.this, e);
            }
        }
    }

    Response getResponseWithInterceptorChain() throws IOException {
        // Build a full stack of interceptors.
        List<Interceptor> interceptors = new ArrayList<>();
        // interceptors.addAll(client.interceptors());
        interceptors.add(new BridgeInterceptor());
        interceptors.add(new CacheInterceptor());
        interceptors.add(new CallServerInterceptor());

        Interceptor.Chain chain = new RealInterceptorChain(interceptors, 0, originalRequest);

        return chain.proceed(originalRequest);
    }
}
