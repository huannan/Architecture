package com.nan.day15_pattern_observer.simple1.observable;

import com.nan.day15_pattern_observer.simple1.observer.IWXUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信公众号： 多个人去订阅我们的公众号
 * WXPublicObservable 和 IWXUser 关系？ 1对多
 * WXPublicObservable 代表的不是具体的公众号，代表的是公众号
 */
public class WXPublicObservable {
    // 所有订阅的用户（公众号用户）
    public List<IWXUser> mWXUsers;

    public WXPublicObservable() {
        mWXUsers = new ArrayList<>();
    }

    /**
     * 订阅
     */
    public void register(IWXUser wxUser) {
        mWXUsers.add(wxUser);
    }

    /**
     * 取消订阅
     *
     * @param wxUser
     */
    public void unregister(IWXUser wxUser) {
        mWXUsers.remove(wxUser);
    }

    /**
     * 更新文章
     *
     * @param article
     */
    public void update(String article) {
        // 推送文章更新
        for (IWXUser wxUser : mWXUsers) {
            wxUser.push(article);
        }
    }
}
