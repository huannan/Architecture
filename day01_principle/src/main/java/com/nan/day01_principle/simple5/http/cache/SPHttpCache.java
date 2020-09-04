package com.nan.day01_principle.simple5.http.cache;

import com.nan.day01_principle.simple5.PreferencesUtil;

public class SPHttpCache implements IHttpCache {

    @Override
    public void saveCache(String url, String json) {
        PreferencesUtil.getInstance().saveParam(url, json);
    }

    @Override
    public String readCache(String url) {
        return (String) PreferencesUtil.getInstance().getObject(url);
    }

}
