package com.nan.day20_pattern_facade.simple2.usersystem.base;

import com.nan.day20_pattern_facade.simple2.UserInfo;

public abstract class BaseUserSystem {

    protected BaseUserSystem mNextUserSystemHandler;

    public BaseUserSystem getNextUserSystemHandler() {
        return mNextUserSystemHandler;
    }

    public void setNextUserSystemHandler(BaseUserSystem nextUserSystemHandler) {
        mNextUserSystemHandler = nextUserSystemHandler;
    }

    /**
     * 对外提供的查询用户信息的方法
     * 通过final实现模板，不给子类重写责任链逻辑
     *
     * @param userName
     * @param userPwd
     * @return
     */
    public final UserInfo queryUserInfo(String userName, String userPwd) {
        // 先自己查一遍
        UserInfo userInfo = queryUserInfoActual(userName, userPwd);
        if (null != userInfo) {
            return userInfo;
        }

        // 查不到交给下一个查询
        if (null != mNextUserSystemHandler) {
            return mNextUserSystemHandler.queryUserInfo(userName, userPwd);
        }

        // 下一个节点为空，直接返回null
        return null;
    }

    /**
     * 这个方法留给子类实现
     *
     * @param userName
     * @param userPwd
     * @return
     */
    protected abstract UserInfo queryUserInfoActual(String userName, String userPwd);

}
