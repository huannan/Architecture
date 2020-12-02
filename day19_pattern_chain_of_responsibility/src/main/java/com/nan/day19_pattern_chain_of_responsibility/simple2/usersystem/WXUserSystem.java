package com.nan.day19_pattern_chain_of_responsibility.simple2.usersystem;

import com.nan.day19_pattern_chain_of_responsibility.simple2.UserInfo;
import com.nan.day19_pattern_chain_of_responsibility.simple2.usersystem.base.BaseUserSystem;
import com.nan.day19_pattern_chain_of_responsibility.simple2.iterator.ArrayIterator;
import com.nan.day19_pattern_chain_of_responsibility.simple2.iterator.Iterator;

/**
 * 微信的用户系统 - 数组存储
 */
public class WXUserSystem extends BaseUserSystem {

    UserInfo[] userInfos;

    public WXUserSystem() {
        userInfos = new UserInfo[3];
        userInfos[0] = new UserInfo("大弟子", "240336124", "001", "男");
        userInfos[1] = new UserInfo("AlvinMoon", "240336124", "002", "男");
        userInfos[2] = new UserInfo("高岩", "240336124", "003", "男");
    }

    @Override
    public UserInfo queryUserInfoActual(String userName, String userPwd) {
        Iterator<UserInfo> iterator = new ArrayIterator(userInfos);
        while (iterator.hasNext()) {
            UserInfo userInfo = iterator.next();
            if (userInfo.userName.equals(userName) && userInfo.userPwd.equals(userPwd)) {
                return userInfo;
            }
        }
        return null;
    }
}
