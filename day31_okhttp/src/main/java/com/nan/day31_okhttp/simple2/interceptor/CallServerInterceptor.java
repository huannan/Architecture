package com.nan.day31_okhttp.simple2.interceptor;

import com.nan.day31_okhttp.simple2.Request;
import com.nan.day31_okhttp.simple2.RequestBody;
import com.nan.day31_okhttp.simple2.Response;
import com.nan.day31_okhttp.simple2.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class CallServerInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        URL url = new URL(request.url());
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        if (urlConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlConnection;
            // https 的一些操作
            // httpsURLConnection.setHostnameVerifier();
            // httpsURLConnection.setSSLSocketFactory();
        }
        // urlConnection.setReadTimeout();

        // 添加缓存 304 ，没有做重定向，没有特殊处理，没有连接复用
        // 如果有拦截器的话那么这些逻辑都可以放到不同的拦截器里面

        // 写东西
        urlConnection.setRequestMethod(request.method());
        urlConnection.setDoOutput(request.doOutput());

        RequestBody requestBody = request.body();
        if (requestBody != null) {
            // 头信息
            urlConnection.setRequestProperty("Content-Type", requestBody.getContentType());
            urlConnection.setRequestProperty("Content-Length", Long.toString(requestBody.getContentLength()));
        }

        urlConnection.connect();

        // 写内容
        if (requestBody != null) {
            requestBody.onWriteBody(urlConnection.getOutputStream());
        }

        int statusCode = urlConnection.getResponseCode();
        // 进行一些列操作，状态码 200
        if (statusCode == 200) {
            // 如果后台返回的数据被gzip压缩过，那么需要使用GZIPInputStream来读
            InputStream inputStream = urlConnection.getInputStream();
            Response response = new Response(statusCode, new ResponseBody(inputStream));
            return response;
        }

        return chain.proceed(request);
    }
}
