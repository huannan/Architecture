package com.nan.day08_pattern_singleton.simple4;

import java.util.HashMap;
import java.util.Map;

/**
 * 容器管理
 * 例如：系统服务SystemServiceRegistry
 */
public class Singleton {

    private static Map<String, Object> sSingleMap = new HashMap<>();

    static {
        sSingleMap.put("a", new Singleton());
    }

    private Singleton() {

    }

    public static Object getService(String name) {
        return sSingleMap.get(name);
    }
}
