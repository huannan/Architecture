package com.flyme.videoclips.day18_pattern_iterator.simple1;

/**
 * 微信的用户系统 - 数组存储
 */
public class WXUserSystem {
    UserInfo[] userInfos;

    public WXUserSystem() {
        userInfos = new UserInfo[3];
        userInfos[0] = new UserInfo("大弟子", "240336124", "001", "男");
        userInfos[1] = new UserInfo("AlvinMoon", "240336124", "002", "男");
        userInfos[2] = new UserInfo("高岩", "240336124", "003", "男");
    }

    public UserInfo[] getUserInfos() {
        return userInfos;
    }
}
