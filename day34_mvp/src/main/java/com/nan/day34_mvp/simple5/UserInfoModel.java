package com.nan.day34_mvp.simple5;

import com.nan.day34_mvp.network.NetworkAPi;
import com.nan.day34_mvp.network.UserInfo;
import com.nan.day34_mvp.simple5.base.BaseModel;

import io.reactivex.Observable;

public class UserInfoModel extends BaseModel {

    /**
     * Model 获取数据
     *
     * @param userId
     * @return
     */
    public Observable<UserInfo> getUser(String userId) {
        return NetworkAPi.getUserInfo(userId);
    }

}
