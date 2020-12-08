package com.nan.day31_okhttp.simple2;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RequestBody {
    // 表单格式
    public static final String FORM = "multipart/form-data";
    private String type;

    private String boundary = createBoundary();
    private String startBoundary = "--" + boundary;
    private String endBoundary = startBoundary + "--";

    private String createBoundary() {
        return "OkHttp" + UUID.randomUUID().toString();
    }

    // 参数，文件
    private Map<String, Object> params;

    public RequestBody() {
        params = new HashMap<>();
    }

    public String getContentType() {
        // 都是一些规范
        return type + ";boundary = " + boundary;
    }

    public long getContentLength() {
        // 多少个字节要给过去，写的内容做一下统计
        long length = 0;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                String postTextStr = getText(key, (String) value);
                Log.e("TAG", postTextStr);
                length += postTextStr.getBytes().length;
            }

            if (value instanceof Bindry) {
                Bindry bindry = (Bindry) value;
                String postTextStr = getText(key, bindry);
                Log.e("TAG", postTextStr);
                length += postTextStr.getBytes().length;
                length += bindry.fileLength() + "\r\n".getBytes().length;
            }
        }

        if (params.size() != 0) {
            length += endBoundary.getBytes().length;
        }
        return length;
    }

    /**
     * startBoundary + "\r\n"
     * Content-Disposition; form-data; name = "pageSize"
     * Context-Type: text/plain
     * <p>
     * <p>
     * 1
     */
    private String getText(String key, String value) {
        return startBoundary + "\r\n" +
                "Content-Disposition: form-data; name = \"" + key + "\"\r\n" +
                "Context-Type: text/plain\r\n" +
                "\r\n" +
                value +
                "\r\n";
    }

    private String getText(String key, Bindry value) {
        return startBoundary + "\r\n" +
                "Content-Disposition: form-data; name = \"" + key + "\" filename = \"" + value.fileName() + "\"" +
                "Context-Type: " + value.mimType() + "\r\n" +
                "\r\n";
    }


    public void onWriteBody(OutputStream outputStream) throws IOException {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                String postTextStr = getText(key, (String) value);
                outputStream.write(postTextStr.getBytes());
            }
            if (value instanceof Bindry) {
                Bindry bindry = (Bindry) value;
                String postTextStr = getText(key, bindry);
                outputStream.write(postTextStr.getBytes());
                bindry.onWrite(outputStream);
                outputStream.write("\r\n".getBytes());
            }
        }

        if (params.size() != 0) {
            outputStream.write(endBoundary.getBytes());
        }
    }

    public RequestBody addParam(String key, String value) {
        params.put(key, value);
        return this;
    }

    public RequestBody addParam(String key, Bindry value) {
        params.put(key, value);
        return this;
    }

    public RequestBody type(String type) {
        this.type = type;
        return this;
    }

    public static Bindry create(final File file) {

        return new Bindry() {
            @Override
            public long fileLength() {
                return file.length();
            }

            @Override
            public String mimType() {
                FileNameMap fileNameMap = URLConnection.getFileNameMap();

                String mimType = fileNameMap.getContentTypeFor(file.getAbsolutePath());

                if (TextUtils.isEmpty(mimType)) {
                    return "application/octet-stream";
                }

                return mimType;
            }

            @Override
            public String fileName() {
                return file.getName();
            }

            @Override
            public void onWrite(OutputStream outputStream) throws IOException {
                InputStream inputStream = new FileInputStream(file);
                byte[] buffer = new byte[2048];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                inputStream.close();
            }
        };
    }
}
