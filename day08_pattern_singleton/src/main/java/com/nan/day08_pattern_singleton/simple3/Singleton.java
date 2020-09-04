package com.nan.day08_pattern_singleton.simple3;

/**
 * 静态内部类
 * 比较常用：线程安全，懒加载
 */
public class Singleton {

    private Singleton() {

    }

    private static class SingletonHolder {
        private static Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
