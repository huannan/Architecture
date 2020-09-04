package com.nan.day01_principle;

import android.app.Application;

import com.nan.day01_principle.simple5.http.HttpHelper;
import com.nan.day01_principle.simple5.http.request.XUtilsRequest;

import org.xutils.x;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        HttpHelper.init(XUtilsRequest.getInstance());
    }
}
