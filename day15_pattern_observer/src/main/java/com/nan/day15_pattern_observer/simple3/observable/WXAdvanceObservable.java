package com.nan.day15_pattern_observer.simple3.observable;

import java.util.Observable;

/**
 * 微信公众号 - Android进阶之旅公众号
 */
public class WXAdvanceObservable extends Observable {
    private String article;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
        // 更新文章 通知 推送
        // 有更新设置改变
        setChanged();
        notifyObservers(article);
    }
}
