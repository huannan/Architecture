package com.nan.day08_pattern_singleton.simple2.sync;

/**
 * 懒汉式
 * 只有在使用的时候才会new对象，可能更加高效
 */
public class Singleton {

    /**
     * 1. 防止重排序
     * 2. 线程可见性
     */
    private static volatile Singleton sInstance;

    private Singleton() {

    }

    /**
     * DCL，解决了多线程并发的问题，但是会出现效率的问题，每次获取都要经过同步锁的判断
     */
    public static synchronized Singleton getInstance1() {
        if (null == sInstance) {
            sInstance = new Singleton();
        }
        return sInstance;
    }

    /**
     * 解决了多线程并发的问题，效率也会相对高一些
     * 但是还是会有问题，必须加上volatile关键字
     */
    public static synchronized Singleton getInstance2() {
        if (null == sInstance) {
            synchronized (Singleton.class) {
                if (null == sInstance) {
                    sInstance = new Singleton();
                }
            }
        }
        return sInstance;
    }
}
