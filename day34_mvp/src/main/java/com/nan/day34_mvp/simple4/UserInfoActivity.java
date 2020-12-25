package com.nan.day34_mvp.simple4;

import android.widget.TextView;

import com.nan.day34_mvp.R;
import com.nan.day34_mvp.network.UserInfo;
import com.nan.day34_mvp.simple4.base.BaseMvpActivity;

/**
 * MVP的最基本实现
 */
public class UserInfoActivity extends BaseMvpActivity<UserInfoPresenter> implements UserInfoView {

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
        getPresenter().getUsers("123456");
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
