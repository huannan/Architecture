package com.nan.day20_pattern_facade.simple2.usersystem;

import com.nan.day20_pattern_facade.simple2.UserInfo;
import com.nan.day20_pattern_facade.simple2.usersystem.base.BaseUserSystem;

public class UserSystemManager {

    private BaseUserSystem mFirstUserSystem;
    private BaseUserSystem mSecondUserSystem;
    private BaseUserSystem mThirdUserSystem;

    public UserSystemManager() {
        mFirstUserSystem = new WXUserSystem();
        mSecondUserSystem = new QQUserSystem();
        mThirdUserSystem = new NYUserSystem();

        mFirstUserSystem.setNextUserSystemHandler(mSecondUserSystem);
        mSecondUserSystem.setNextUserSystemHandler(mThirdUserSystem);
    }

    public UserInfo queryUserInfo(String userName, String userPwd) {
        return mFirstUserSystem.queryUserInfo(userName, userPwd);
    }

}
