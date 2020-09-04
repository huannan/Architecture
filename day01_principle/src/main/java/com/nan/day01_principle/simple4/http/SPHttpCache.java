package com.nan.day01_principle.simple4.http;

import com.nan.day01_principle.simple4.PreferencesUtil;

public class SPHttpCache {

    public void saveCache(String url, String json) {
        PreferencesUtil.getInstance().saveParam(url, json);
    }

    public String readCache(String url) {
        return (String) PreferencesUtil.getInstance().getObject(url);
    }

}
