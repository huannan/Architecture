package com.nan.day19_pattern_chain_of_responsibility.simple2.usersystem;

import com.nan.day19_pattern_chain_of_responsibility.simple2.UserInfo;
import com.nan.day19_pattern_chain_of_responsibility.simple2.usersystem.base.BaseUserSystem;
import com.nan.day19_pattern_chain_of_responsibility.simple2.iterator.Iterator;
import com.nan.day19_pattern_chain_of_responsibility.simple2.iterator.ListIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 农药系统 - 列表存储
 */
public class NYUserSystem extends BaseUserSystem {
    private List<UserInfo> userInfos;

    public NYUserSystem() {
        userInfos = new ArrayList<>();
        userInfos.add(new UserInfo("Wenld", "240336124", "001", "男"));
        userInfos.add(new UserInfo("yuFrank", "240336124", "002", "男"));
        userInfos.add(new UserInfo("葡萄我爱吃", "240336124", "003", "男"));
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
