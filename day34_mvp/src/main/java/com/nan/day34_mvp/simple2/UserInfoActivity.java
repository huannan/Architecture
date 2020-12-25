package com.nan.day34_mvp.simple2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nan.day34_mvp.R;
import com.nan.day34_mvp.network.UserInfo;

/**
 * MVP的最基本实现
 */
public class UserInfoActivity extends AppCompatActivity implements UserInfoView {

    private UserInfoPresenter mPresenter;
    private TextView mTvUserInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mTvUserInfo = findViewById(R.id.tvUserInfo);

        mPresenter = new UserInfoPresenter();
        mPresenter.attach(this);
        mPresenter.getUsers("123456");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    @Override
    public void onLoading() {
        mTvUserInfo.setText("加载中");
    }

    @Override
    public void onError(Throwable e) {
        mTvUserInfo.setText("加载异常");
    }

    /**
     * 这个时候 Activity 有可能会被关闭掉，有可能会异常崩溃（一般不会）
     * 1. 可以判断界面还在不在(试一试)
     * 2. 采用解绑（通用）
     *
     * @param userInfo
     */
    @Override
    public void onSuccess(UserInfo userInfo) {
        mTvUserInfo.setText(userInfo.getName());
    }
}
