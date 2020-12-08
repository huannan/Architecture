package com.nan.day31_okhttp.simple2;

import java.io.IOException;
import java.io.OutputStream;

public interface Bindry {
    long fileLength();

    String mimType();

    String fileName();

    void onWrite(OutputStream outputStream) throws IOException;
}
