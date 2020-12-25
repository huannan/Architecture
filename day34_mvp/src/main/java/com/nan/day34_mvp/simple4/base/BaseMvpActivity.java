package com.nan.day34_mvp.simple4.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        // 创建 P，创建只能交给 子类，每个 Activity 都不一样
        mPresenter = createPresenter();
        mPresenter.attach(this);

        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    protected abstract void setContentView();

    /**
     * 由子类去实现创建
     * @return
     */
    protected abstract P createPresenter();

    protected abstract void initView();

    protected abstract void initData();

    public P getPresenter() {
        return mPresenter;
    }
}