package com.nan.day08_pattern_singleton.simple2;

/**
 * 懒汉式
 * 只有在使用的时候才会new对象，可能更加高效
 * 但是会有多线程并发的问题
 */
public class Singleton {

    private static Singleton sInstance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (null == sInstance) {
            sInstance = new Singleton();
        }
        return sInstance;
    }
}
