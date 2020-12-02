package com.nan.day18_pattern_iterator.simple1;

import java.util.ArrayList;
import java.util.List;

/**
 * QQ用户系统 - 列表存储
 */
public class QQUserSystem {
    private List<UserInfo> userInfos;

    public QQUserSystem() {
        userInfos = new ArrayList<>();
        userInfos.add(new UserInfo("Huannan", "240336124", "001", "男"));
        userInfos.add(new UserInfo("夕决", "240336124", "002", "男"));
        userInfos.add(new UserInfo("yjy239", "240336124", "003", "男"));
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }
}
