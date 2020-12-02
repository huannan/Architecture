package com.nan.day18_pattern_iterator.simple2.iterator;

import com.nan.day18_pattern_iterator.simple2.UserInfo;

/**
 * 微信的具体的迭代器
 */
public class WXIterator implements Iterator<UserInfo> {

    private UserInfo[] userInfos;
    private int index = 0;

    public WXIterator(UserInfo[] userInfos) {
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
