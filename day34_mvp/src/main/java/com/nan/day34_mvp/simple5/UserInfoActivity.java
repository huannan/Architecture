package com.nan.day34_mvp.simple5;

import android.widget.TextView;

import com.nan.day34_mvp.R;
import com.nan.day34_mvp.network.UserInfo;
import com.nan.day34_mvp.simple5.base.BaseMvpActivity;
import com.nan.day34_mvp.simple5.inject.InjectPresenter;

/**
 * MVP的最基本实现
 *
 * 解决每次都要手动创建的问题:
 * 动态创建M
 * 通过注入的方式动态创建Presenter
 */
public class UserInfoActivity extends BaseMvpActivity<UserInfoPresenter> implements UserInfoView {

    @InjectPresenter
    private UserInfoPresenter mPresenter;
    private TextView mTvUserInfo;

    @Override
    protected UserInfoPresenter createPresenter() {
        return new UserInfoPresenter();
    }


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_user_info);
    }


    @Override
    protected void initView() {
        mTvUserInfo = findViewById(R.id.tvUserInfo);
    }

    @Override
    protected void initData() {
        mPresenter.getUsers("123456");
    }


    @Override
    public void onLoading() {
        mTvUserInfo.setText("加载中");
    }

    @Override
    public void onError(Throwable e) {
        mTvUserInfo.setText("加载异常");
    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        mTvUserInfo.setText(userInfo.getName());
    }
}
