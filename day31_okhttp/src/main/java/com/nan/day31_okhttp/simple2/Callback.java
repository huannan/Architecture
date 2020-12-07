package com.nan.day31_okhttp.simple2;

import java.io.IOException;

public interface Callback {

    void onFailure(okhttp3.Call call, IOException e);

    void onResponse(Call call, Response response) throws IOException;

}
