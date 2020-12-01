package com.flyme.videoclips.day18_pattern_iterator.simple2;

import com.flyme.videoclips.day18_pattern_iterator.simple2.iterator.Iterator;
import com.flyme.videoclips.day18_pattern_iterator.simple2.iterator.QQIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * QQ用户系统 - 列表存储
 */
public class QQUserSystem implements Aggregate<UserInfo> {

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

    /**
     * 只提供迭代方法，不暴露具体实现
     *
     * @return
     */
    @Override
    public Iterator<UserInfo> iterator() {
        return new QQIterator(userInfos);
    }
}
