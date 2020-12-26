package com.nan.day34_mvp.simple6;

import com.nan.day34_mvp.network.UserInfo;
import com.nan.day34_mvp.simple6.base.BasePresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserInfoPresenter extends BasePresenter<UserInfoView, UserInfoModel> {

    void getUsers(String userId) {
        getModel().getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // 每次使用View之前都判断一下,很麻烦
                        UserInfoView view = getView();
                        if (null != view) {
                            view.onLoading();
                        }
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        UserInfoView view = getView();
                        if (null != view) {
                            view.onSuccess(userInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        UserInfoView view = getView();
                        if (null != view) {
                            view.onError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
