package com.nan.day34_mvp.network;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class NetworkAPi {

    /**
     * 模拟网络请求
     *
     * @param userId
     */
    public static Observable<UserInfo> getUserInfo(String userId) {
        return Observable.just(new UserInfo("Huannan"))
                .doOnNext(new Consumer<UserInfo>() {
                    @Override
                    public void accept(UserInfo userInfo) throws Exception {
                        Thread.sleep(3000L);
                    }
                });
    }

}
