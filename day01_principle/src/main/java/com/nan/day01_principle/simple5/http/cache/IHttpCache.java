package com.nan.day01_principle.simple5.http.cache;

public interface IHttpCache {
    void saveCache(String url, String json);

    String readCache(String url);
}
