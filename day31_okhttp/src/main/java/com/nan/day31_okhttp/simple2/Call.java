package com.nan.day31_okhttp.simple2;

import java.io.IOException;

public interface Call {

    /**
     * 同步
     */
    Response execute() throws IOException;

    /**
     * 异步
     */
    void enqueue(Callback responseCallback);

    void cancel();
}
