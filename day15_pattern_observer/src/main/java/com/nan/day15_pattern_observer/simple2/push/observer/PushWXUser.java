package com.nan.day15_pattern_observer.simple2.push.observer;

/**
 * 微信公众号 - 具体订阅用户（Darren，高岩）
 */
public class PushWXUser implements IPushWXUser {

    private String name;

    public PushWXUser(String name) {
        this.name = name;
    }

    @Override
    public void push(String article) {
        System.out.println(name + " 收到了一篇推送文章：" + article);
    }
}
