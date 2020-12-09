package com.nan.day31_okhttp.simple3;

/**
 * Created by hcDarren on 2017/11/25.
 */
public interface UploadProgressListener {
    void onProgress(long total, long current, int progress);
}
