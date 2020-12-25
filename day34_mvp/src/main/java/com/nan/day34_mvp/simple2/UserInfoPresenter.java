package com.nan.day34_mvp.simple2;

import com.nan.day34_mvp.network.UserInfo;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserInfoPresenter {

    // 因为Presenter是解耦层,所以肯定会持有 V 和 M
    private UserInfoView mView;
    private UserInfoModel mModel;


    /**
     * 关于绑定和解绑 View 加了attach和detach之后越来越复杂，代码写起来越来越多？怎么办？
     * 问题是:
     * 1. 很多代码是公用反复的，attach detach 每个 Presenter 都要有
     * 2. Activity -> View 的 attach detach 每个 View 层也要有
     * <p>
     * 解决办法: 泛型构建基类
     */
    public void attach(UserInfoView view) {
        mView = view;
    }

    public void detach() {
        mView = null;
    }

    public UserInfoPresenter() {
        mModel = new UserInfoModel();
    }

    void getUsers(String userId) {
        mModel.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // 每次使用View之前都判断一下
                        if (null != mView) {
                            mView.onLoading();
                        }
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        if (null != mView) {
                            mView.onSuccess(userInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null != mView) {
                            mView.onError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
