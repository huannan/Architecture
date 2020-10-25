package com.nan.day15_pattern_observer.simple3.observable;

import com.darren.architect_day_15.simple3.observer.IWXUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcDarren on 2017/10/14. 微信公众号
 * 微信公众号： 多个人去订阅我们的公众
 * 这个类不用了用系统Java自带的 Observable
 */
public class WXPublicObservable {
    // 所有订阅的用户
    public List<IWXUser> mWXUsers;

    public WXPublicObservable(){
        mWXUsers = new ArrayList<>();
    }

    /**
     * 订阅
     */
    public void register(IWXUser wxUser){
        mWXUsers.add(wxUser);
    }

    /**
     * 取消订阅
     * @param wxUser
     */
    public void unregister(IWXUser wxUser){
        mWXUsers.remove(wxUser);
    }

    /**
     * 更新文章
     * @param article
     */
    public void update(String article){
        // 推送文章更新
        for (IWXUser wxUser : mWXUsers) {
            wxUser.push(article);
        }
    }
}
