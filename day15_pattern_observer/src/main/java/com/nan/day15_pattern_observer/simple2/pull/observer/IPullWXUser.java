package com.nan.day15_pattern_observer.simple2.pull.observer;

import com.nan.day15_pattern_observer.simple2.pull.observable.PullWXPublicObservable;

/**
 * 微信公众号 - 微信用户
 */
public interface IPullWXUser {
    /**
     * 把公众号给用户
     */
    void pull(PullWXPublicObservable wxObservable);
}
