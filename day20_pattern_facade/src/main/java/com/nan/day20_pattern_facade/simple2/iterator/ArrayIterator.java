package com.nan.day20_pattern_facade.simple2.iterator;

import com.nan.day20_pattern_facade.simple2.UserInfo;

/**
 * 微信的具体的迭代器
 */
public class ArrayIterator implements Iterator<UserInfo> {

    private UserInfo[] userInfos;
    private int index = 0;

    public ArrayIterator(UserInfo[] userInfos) {
        this.userInfos = userInfos;
    }

    @Override
    public UserInfo next() {
        return userInfos[index++];
    }

    @Override
    public boolean hasNext() {
        return index < userInfos.length;
    }
}
