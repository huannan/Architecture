package com.nan.day01_principle.simple3;

public class PreferencesHttpCache{
    /**
     * 获取数据
     */
    public String getCacheResultJson(String finalUrl) {
        return (String) PreferencesUtil.getInstance().getParam(finalUrl, "");
    }

    /**
     * 缓存数据
     */
    public long cacheData(String finalUrl, String resultJson) {
        PreferencesUtil.getInstance().saveParam(finalUrl, resultJson);
        return 1;
    }
}
