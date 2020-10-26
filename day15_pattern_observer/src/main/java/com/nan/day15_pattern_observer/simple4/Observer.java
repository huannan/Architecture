package com.nan.day15_pattern_observer.simple4;

/**
 * 观察者
 */
public interface Observer<T> {
    void update(T t);
}
