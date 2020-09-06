package com.nan.day10_pattern_factory.simple4.io.handler;

import android.support.v4.util.LruCache;

public class MemoryIOHandler implements IOHandler {

    //最好的APP运行大小的八分之一
    private static LruCache<String, Object> mCache = new LruCache<>(10 * 1024 * 1024);

    @Override
    public void save(String key, String value) {
        mCache.put(key, value);
    }

    @Override
    public void save(String key, double value) {

    }

    @Override
    public void save(String key, int value) {

    }

    @Override
    public void save(String key, long value) {

    }

    @Override
    public void save(String key, boolean value) {

    }

    @Override
    public void save(String key, Object value) {

    }

    @Override
    public String getString(String key) {
        return (String) mCache.get(key);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return 0;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return 0;
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return 0;
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return false;
    }

    @Override
    public Object getObject(String key) {
        return null;
    }
}
