package com.nan.day20_pattern_facade.simple2.usersystem;

import com.nan.day20_pattern_facade.simple2.UserInfo;
import com.nan.day20_pattern_facade.simple2.iterator.Iterator;
import com.nan.day20_pattern_facade.simple2.iterator.ListIterator;
import com.nan.day20_pattern_facade.simple2.usersystem.base.BaseUserSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * QQ用户系统 - 列表存储
 */
public class QQUserSystem extends BaseUserSystem {

    private List<UserInfo> userInfos;

    public QQUserSystem() {
        userInfos = new ArrayList<>();
        userInfos.add(new UserInfo("Huannan", "240336124", "001", "男"));
        userInfos.add(new UserInfo("夕决", "240336124", "002", "男"));
        userInfos.add(new UserInfo("yjy239", "240336124", "003", "男"));
    }

    @Override
    public UserInfo queryUserInfoActual(String userName, String userPwd) {
        Iterator<UserInfo> iterator = new ListIterator(userInfos);
        while (iterator.hasNext()) {
            UserInfo userInfo = iterator.next();
            if (userInfo.userName.equals(userName) && userInfo.userPwd.equals(userPwd)) {
                return userInfo;
            }
        }
        return null;
    }
}
