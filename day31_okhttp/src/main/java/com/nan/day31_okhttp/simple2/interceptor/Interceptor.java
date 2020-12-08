package com.nan.day31_okhttp.simple2.interceptor;

import com.nan.day31_okhttp.simple2.Request;
import com.nan.day31_okhttp.simple2.Response;

import java.io.IOException;

public interface Interceptor {

    Response intercept(Interceptor.Chain chain) throws IOException;

    interface Chain {
        Request request();

        Response proceed(Request request) throws IOException;
    }
}
