package com.nan.day34_mvp.simple6;

import com.nan.day34_mvp.network.UserInfo;
import com.nan.day34_mvp.simple6.base.BaseView;

public interface UserInfoView extends BaseView {
    // 1.正在加载中
    // 2.获取出错了
    // 3.成功了要显示数据
    void onLoading();

    void onError(Throwable e);

    void onSuccess(UserInfo userInfo);
}
