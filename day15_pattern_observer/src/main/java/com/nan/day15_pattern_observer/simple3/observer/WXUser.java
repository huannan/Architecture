package com.nan.day15_pattern_observer.simple3.observer;

import com.darren.architect_day_15.simple3.observable.WXAdvanceObservable;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by hcDarren on 2017/10/14.
 * 微信公众号 - 具体订阅用户（Darren，高岩）
 */

public class WXUser implements Observer {

    private String name;

    public WXUser(String name) {
        this.name = name;
    }

    /*@Override
    public void push(String article) {
        System.out.println(name + " 收到了一篇文章：" + article);
    }*/

    // 推拉模式 = 既可以拉又可以推
    @Override
    public void update(Observable o, Object arg) {
        // o 是 具体的公众号 也就是 WXAdvanceObservable
        // arg 是 推动的文章 article
        System.out.println(name + " 收到了一篇推送的文章：" + arg);

        WXAdvanceObservable advanceObservable = (WXAdvanceObservable) o;
        System.out.println(name + " 主动拉取一篇文章：" + advanceObservable.getArticle());
    }
}
