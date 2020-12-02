package com.nan.day20_pattern_facade.simple2.iterator;

import com.nan.day20_pattern_facade.simple2.UserInfo;

import java.util.List;

/**
 * QQ的具体的迭代器
 */
public class ListIterator implements Iterator<UserInfo> {

    private List<UserInfo> userInfos;
    private int index = 0;

    public ListIterator(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    @Override
    public UserInfo next() {
        return userInfos.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < userInfos.size();
    }
}
