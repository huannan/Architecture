package com.nan.day31_okhttp.simple2;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求
 */
public class Request {

    final String url;
    // 请求方法
    @Method
    final String method;
    // 请求体
    final RequestBody body;
    // 请求头，注意实际上一个key要对应多个value
    final Map<String, String> headers;

    private Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.body = builder.body;
        this.headers = builder.headers;
    }

    public boolean doOutput() {
        return Method.POST.equals(method) || Method.PUT.equals(method);
    }

    public String url() {
        return url;
    }

    public String method() {
        return method;
    }

    public RequestBody body() {
        return body;
    }

    public Map<String, String> headers() {
        return headers;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public static class Builder {

        // url、body、headers等

        // 源码中的是HttpUrl，需要进行域名、端口、协议等参数的解析，因为OkHttp自己封装了Socket
        String url;
        // 请求方法
        @Method
        String method;
        // 请求体
        RequestBody body;
        // 请求头，注意实际上一个key要对应多个value
        Map<String, String> headers;

        public Builder() {
            method = Method.GET;
            headers = new HashMap<>();
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder get() {
            this.method = Method.GET;
            return this;
        }

        public Builder post(RequestBody body) {
            this.method = Method.POST;
            this.body = body;
            return this;
        }

        public Builder header(String key, String value) {
            headers.put(key, value);
            return this;
        }

        public Request build() {
            return new Request(this);
        }

    }

}
