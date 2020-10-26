package com.nan.day15_pattern_observer.simple3;

import com.nan.day15_pattern_observer.simple3.observable.WXAdvanceObservable;
import com.nan.day15_pattern_observer.simple3.observer.WXUser;

public class Client {
    public static void main(String[] args) {
        // 微信公众号 - 具体的被观察者 - Android进阶之旅
        WXAdvanceObservable wxAdvanceObservable = new WXAdvanceObservable();

        // 微信公众号 - 具体的观察者 - Darren，高岩
        WXUser darren = new WXUser("darren");
        WXUser gaoyan = new WXUser("高岩");

        // 微信公众号 - 用户订阅公众号
        wxAdvanceObservable.addObserver(darren);
        wxAdvanceObservable.addObserver(gaoyan);

        // 微信公众号 - 推送文章
        wxAdvanceObservable.setArticle("《代理设计模式 - 实现 Retrofit 的 create》");

        // 微信公众号 - 用户取消订阅公众号
        wxAdvanceObservable.deleteObserver(darren);

        // 微信公众号 - 推送文章
        wxAdvanceObservable.setArticle("第三方框架 - EventBus 源码分析和手写");
    }

    // RXJava + OKHttp + Retrofit
}
