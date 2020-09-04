package com.nan.day08_pattern_singleton.simple1;

/**
 * 饿汉式
 * 随着类的加载就已经new了对象
 */
public class Singleton {

    private static Singleton sInstance = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance() {
        return sInstance;
    }
}
