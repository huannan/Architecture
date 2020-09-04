package com.nan.day06_apt_pay;

import android.app.Application;

import com.nan.day06_apt_pay_annotations.WXPayEntry;
import com.nan.day06_apt_pay_core.BaseWxPayEntryActivity;

@WXPayEntry(packageName = "com.nan.day06_apt_pay", baseEntryActivity = BaseWxPayEntryActivity.class)
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
