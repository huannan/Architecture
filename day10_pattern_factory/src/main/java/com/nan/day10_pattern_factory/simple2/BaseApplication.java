package com.nan.day10_pattern_factory.simple2;

import android.app.Application;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesUtils.getInstance().init(this);
    }
}
