package com.nan.day15_pattern_observer.simple2.pull.observable;

import com.nan.day15_pattern_observer.simple2.pull.observer.IPullWXUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信公众号： 多个人去订阅我们的公众
 */
public class PullWXPublicObservable {
    // 所有订阅的用户
    public List<IPullWXUser> mWXUsers;

    public PullWXPublicObservable() {
        mWXUsers = new ArrayList<>();
    }

    /**
     * 订阅
     */
    public void register(IPullWXUser wxUser) {
        mWXUsers.add(wxUser);
    }

    /**
     * 取消订阅
     *
     * @param wxUser
     */
    public void unregister(IPullWXUser wxUser) {
        mWXUsers.remove(wxUser);
    }

    /**
     * 更新文章
     *
     * @param article
     */
    public void update(String article) {
        // 推送文章更新
        for (IPullWXUser wxUser : mWXUsers) {
            wxUser.pull(this);
        }
    }
}
