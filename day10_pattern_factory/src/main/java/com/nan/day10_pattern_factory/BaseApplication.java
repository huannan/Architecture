package com.nan.day10_pattern_factory;

import android.app.Application;

import com.nan.day10_pattern_factory.simple2.PreferencesUtils;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesUtils.getInstance().init(this);
    }
}
