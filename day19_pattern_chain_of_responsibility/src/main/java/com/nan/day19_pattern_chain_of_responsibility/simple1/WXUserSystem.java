package com.nan.day19_pattern_chain_of_responsibility.simple1;

import com.nan.day19_pattern_chain_of_responsibility.simple1.iterator.Iterator;
import com.nan.day19_pattern_chain_of_responsibility.simple1.iterator.ArrayIterator;

/**
 * 微信的用户系统 - 数组存储
 */
public class WXUserSystem implements Aggregate<UserInfo> {

    UserInfo[] userInfos;

    public WXUserSystem() {
        userInfos = new UserInfo[3];
        userInfos[0] = new UserInfo("大弟子", "240336124", "001", "男");
        userInfos[1] = new UserInfo("AlvinMoon", "240336124", "002", "男");
        userInfos[2] = new UserInfo("高岩", "240336124", "003", "男");
    }

    /**
     * 只提供迭代方法，不暴露具体实现
     *
     * @return
     */
    @Override
    public Iterator<UserInfo> iterator() {
        return new ArrayIterator(userInfos);
    }
}
