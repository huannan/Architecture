package com.nan.day01_principle.simple5.http.cache;

import java.util.HashMap;
import java.util.Map;

public class MemoryHttpCache implements IHttpCache {

    private static Map<String, String> sMemoryCache = new HashMap<>();

    @Override
    public void saveCache(String url, String json) {
        sMemoryCache.put(url, json);
    }

    @Override
    public String readCache(String url) {
        return sMemoryCache.get(url);
    }

}
