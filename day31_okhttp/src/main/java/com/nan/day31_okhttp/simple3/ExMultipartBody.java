package com.nan.day31_okhttp.simple3;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;

/**
 * 通过静态代理设计模式来
 */
public class ExMultipartBody extends RequestBody {

    private static final String TAG = "ExMultipartBody";
    private RequestBody mDelegate;
    private UploadProgressListener mListener;
    private long mCurrentByteCount;

    public ExMultipartBody(RequestBody requestBody, UploadProgressListener listener) {
        mDelegate = requestBody;
        mListener = listener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        // 静态代理最终调用的是被代理对象的方法
        return mDelegate.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        // 静态代理最终调用的是被代理对象的方法
        return mDelegate.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        // 总长度
        final long contentLength = contentLength();
        // 获取当前的进度，又来一个代理 ForwardingSink
        ForwardingSink forwardingSink = new ForwardingSink(sink) {
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                // 每次写都会来这里
                mCurrentByteCount += byteCount;
                if (null != mListener) {
                    mListener.onProgress(contentLength, mCurrentByteCount, (int) (mCurrentByteCount * 100 / contentLength));
                }
                super.write(source, byteCount);
            }
        };
        // ForwardingSink转BufferedSink
        BufferedSink bufferedSink = Okio.buffer(forwardingSink);
        mDelegate.writeTo(bufferedSink);
        // 刷新，RealConnection 连接池
        bufferedSink.flush();
    }
}
