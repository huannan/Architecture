package com.nan.day08_pattern_singleton.simple5;

/**
 * 自成一派
 */
public class Singleton {

    private static Singleton sInstance;

    static {
        sInstance = new Singleton();
    }

    private Singleton() {

    }

    public static Singleton getInstance() {
        return sInstance;
    }
}
