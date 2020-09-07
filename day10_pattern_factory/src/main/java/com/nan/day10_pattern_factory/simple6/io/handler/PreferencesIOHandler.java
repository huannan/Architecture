package com.nan.day10_pattern_factory.simple6.io.handler;

import com.nan.day10_pattern_factory.simple2.PreferencesUtils;

public class PreferencesIOHandler implements IOHandler {

    private PreferencesUtils mPreferencesUtils = PreferencesUtils.getInstance();

    @Override
    public void save(String key, String value) {
        mPreferencesUtils.putString(key, value)
                .commit();
    }

    @Override
    public void save(String key, double value) {

    }

    @Override
    public void save(String key, int value) {
        mPreferencesUtils.putInt(key, value)
                .commit();
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
        return mPreferencesUtils.getString(key, null);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return 0;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return mPreferencesUtils.getInt(key, defaultValue);
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
