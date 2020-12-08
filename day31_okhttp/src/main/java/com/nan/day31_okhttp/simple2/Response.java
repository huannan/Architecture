package com.nan.day31_okhttp.simple2;

/**
 * 响应
 */
public class Response {

    int code;
    ResponseBody body;

    public Response(int code, ResponseBody body) {
        this.code = code;
        this.body = body;
    }

    public ResponseBody body() {
        return body;
    }

    public int code() {
        return code;
    }
}
