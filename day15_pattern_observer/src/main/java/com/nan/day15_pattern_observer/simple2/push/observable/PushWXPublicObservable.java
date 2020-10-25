package com.nan.day15_pattern_observer.simple2.push.observable;

import com.nan.day15_pattern_observer.simple2.push.observer.IPushWXUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信公众号： 多个人去订阅我们的公众
 */
public class PushWXPublicObservable {
    // 所有订阅的用户
    public List<IPushWXUser> mWXUsers;

    public PushWXPublicObservable() {
        mWXUsers = new ArrayList<>();
    }

    /**
     * 订阅
     */
    public void register(IPushWXUser wxUser) {
        mWXUsers.add(wxUser);
    }

    /**
     * 取消订阅
     *
     * @param wxUser
     */
    public void unregister(IPushWXUser wxUser) {
        mWXUsers.remove(wxUser);
    }

    /**
     * 更新文章
     *
     * @param article
     */
    public void update(String article) {
        // 推送文章更新
        for (IPushWXUser wxUser : mWXUsers) {
            wxUser.push(article);
        }
    }
}
