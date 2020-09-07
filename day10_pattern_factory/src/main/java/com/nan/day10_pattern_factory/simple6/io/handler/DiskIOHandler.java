package com.nan.day10_pattern_factory.simple6.io.handler;

public class DiskIOHandler implements IOHandler {

    @Override
    public void save(String key, String value) {

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
        return null;
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
