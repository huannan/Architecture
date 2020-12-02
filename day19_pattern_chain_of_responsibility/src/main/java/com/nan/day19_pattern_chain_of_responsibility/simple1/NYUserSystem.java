package com.nan.day19_pattern_chain_of_responsibility.simple1;

import com.nan.day19_pattern_chain_of_responsibility.simple1.iterator.Iterator;
import com.nan.day19_pattern_chain_of_responsibility.simple1.iterator.ListIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 农药系统 - 列表存储
 */
public class NYUserSystem implements Aggregate<UserInfo> {
    private List<UserInfo> userInfos;

    public NYUserSystem() {
        userInfos = new ArrayList<>();
        userInfos.add(new UserInfo("Wenld", "240336124", "001", "男"));
        userInfos.add(new UserInfo("yuFrank", "240336124", "002", "男"));
        userInfos.add(new UserInfo("葡萄我爱吃", "240336124", "003", "男"));
    }

    @Override
    public Iterator<UserInfo> iterator() {
        return new ListIterator(userInfos);
    }
}
