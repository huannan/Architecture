package com.nan.day10_pattern_factory.simple2;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils {

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private PreferencesUtils() {

    }

    private static class Holder {
        private static PreferencesUtils INSTANCE = new PreferencesUtils();
    }

    public static PreferencesUtils getInstance() {
        return Holder.INSTANCE;
    }

    public void init(Context context) {
        mPreferences = context.getApplicationContext().getSharedPreferences("cache", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public PreferencesUtils putString(String key, String value) {
        mEditor.putString(key, value);
        return this;
    }

    public PreferencesUtils putInt(String key, int value) {
        mEditor.putInt(key, value);
        return this;
    }

    public void commit() {
        mEditor.commit();
    }

    public String getString(String key, String defValue) {
        return mPreferences.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mPreferences.getInt(key, defValue);
    }
}
