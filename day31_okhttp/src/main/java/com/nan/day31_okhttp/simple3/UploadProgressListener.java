package com.nan.day31_okhttp.simple3;

public interface UploadProgressListener {

    void onProgress(long total, long current, int progress);

}
