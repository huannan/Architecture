package com.nan.day20_pattern_facade.simple2;

import com.nan.day20_pattern_facade.simple2.usersystem.UserSystemManager;

/**
 * 采用责任链设计模式
 */
public class Client {
    public static void main(String[] args) {
        // 根据用户名和密码去查询用户信息，
        // 如果没有查询到那么代表登录失败，如果查询到了代表登录成功
        String userName = "葡萄我爱吃";
        String userPwd = "240336124";

        UserSystemManager manager = new UserSystemManager();

        UserInfo loginUserInfo = manager.queryUserInfo(userName, userPwd);

        if (loginUserInfo == null) {
            // 登录失败，用户名和密码错误
            System.out.println("登录失败");
        } else {
            // 登录成功
            System.out.println("登录成功");
        }
    }

}
