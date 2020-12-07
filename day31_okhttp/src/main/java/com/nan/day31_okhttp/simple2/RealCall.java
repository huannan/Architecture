package com.nan.day31_okhttp.simple2;

import android.util.Log;

import java.io.IOException;

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

    static final class AsyncCall extends NameRunnable {

        private final Callback responseCallback;

        public AsyncCall(Callback responseCallback) {
            this.responseCallback = responseCallback;
        }

        @Override
        public void execute() {
            Log.e(OkHttpClient.TAG, "开始网络请求");
            // 基于HttpUrlConnection，而OkHttp是Socket+OkIO

        }
    }
}
