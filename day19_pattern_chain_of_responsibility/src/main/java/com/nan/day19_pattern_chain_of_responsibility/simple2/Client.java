package com.nan.day19_pattern_chain_of_responsibility.simple2;

import com.nan.day19_pattern_chain_of_responsibility.simple2.usersystem.NYUserSystem;
import com.nan.day19_pattern_chain_of_responsibility.simple2.usersystem.QQUserSystem;
import com.nan.day19_pattern_chain_of_responsibility.simple2.usersystem.WXUserSystem;

/**
 * 采用责任链设计模式
 */
public class Client {
    public static void main(String[] args) {
        // 根据用户名和密码去查询用户信息，
        // 如果没有查询到那么代表登录失败，如果查询到了代表登录成功
        String userName = "葡萄我爱吃";
        String userPwd = "240336124";

        WXUserSystem wxUserSystem = new WXUserSystem();
        QQUserSystem qqUserSystem = new QQUserSystem();
        NYUserSystem nyUserSystem = new NYUserSystem();
        wxUserSystem.setNextUserSystemHandler(qqUserSystem);
        qqUserSystem.setNextUserSystemHandler(nyUserSystem);

        UserInfo loginUserInfo = wxUserSystem.queryUserInfo(userName, userPwd);

        if (loginUserInfo == null) {
            // 登录失败，用户名和密码错误
            System.out.println("登录失败");
        } else {
            // 登录成功
            System.out.println("登录成功");
        }
    }

}
