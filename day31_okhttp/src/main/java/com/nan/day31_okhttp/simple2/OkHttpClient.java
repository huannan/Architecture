package com.nan.day31_okhttp.simple2;

/**
 * 涉及的代码比较多，先画UML或者画架构图，后面再慢慢补充
 */
public class OkHttpClient {

    public static final String TAG = "OkHttpClient";
    final Dispatcher dispatcher;

    public OkHttpClient() {
        this(new Builder());
    }

    public OkHttpClient(Builder builder) {
        this.dispatcher = builder.dispatcher;
    }

    public Call newCall(Request request) {
        return RealCall.newCall(this, request);
    }

    public static class Builder {

        // 线程池处理
        Dispatcher dispatcher;
        // 此处还有很多其他参数，连接超时、https证书相关的参数、拦截器

        public Builder() {
            this.dispatcher = new Dispatcher();
        }

        public OkHttpClient build() {
            return new OkHttpClient(this);
        }

    }

}
