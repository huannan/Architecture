package com.flyme.videoclips.day18_pattern_iterator.simple2;

import com.flyme.videoclips.day18_pattern_iterator.simple2.iterator.Iterator;

/**
 * 采用迭代器设计模式
 */
public class Client {
    public static void main(String[] args) {
        // 根据用户名和密码去查询用户信息，
        // 如果没有查询到那么代表登录失败，如果查询到了代表登录成功
        String userName = "高岩";
        String userPwd = "240336124";

        WXUserSystem wxUserSystem = new WXUserSystem();
        QQUserSystem qqUserSystem = new QQUserSystem();

        // 两个系统统一放到一个地方 // 责任链模式讲解
        UserInfo loginUserInfo = queryUserInfo(userName, userPwd, wxUserSystem.iterator());

        if (loginUserInfo == null) {
            // 从QQ的系统里面去获取
            loginUserInfo = queryUserInfo(userName, userPwd, qqUserSystem.iterator());
        }

        // 很有可能会接第三个系统，或者说还有第四个系统，这里就会越来越复杂

        if (loginUserInfo == null) {
            // 登录失败，用户名和密码错误
            System.out.println("登录失败");
        } else {
            // 登录成功
            System.out.println("登录成功");
        }
    }

    /**
     * 查询用户信息
     *
     * @param userName
     * @param userPwd
     * @param iterator
     * @return
     */
    private static UserInfo queryUserInfo(String userName, String userPwd, Iterator<UserInfo> iterator) {
        while (iterator.hasNext()) {
            UserInfo userInfo = iterator.next();
            if (userInfo.userName.equals(userName) && userInfo.userPwd.equals(userPwd)) {
                return userInfo;
            }
        }
        return null;
    }

}
