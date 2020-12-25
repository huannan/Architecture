package com.nan.day34_mvp.simple2;

import com.nan.day34_mvp.network.UserInfo;

public interface UserInfoView {
    // 1.正在加载中
    // 2.获取出错了
    // 3.成功了要显示数据
    void onLoading();
    void onError(Throwable e);
    void onSuccess(UserInfo userInfo);
}
