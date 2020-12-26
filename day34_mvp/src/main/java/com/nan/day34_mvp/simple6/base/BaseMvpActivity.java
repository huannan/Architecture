package com.nan.day34_mvp.simple6.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nan.day34_mvp.simple6.proxy.ActivityMvpProxyImpl;
import com.nan.day34_mvp.simple6.proxy.IMvpProxy;

public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    private P mPresenter;
    private IMvpProxy mMvpProxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        // 创建 P，创建只能交给 子类，每个 Activity 都不一样
        mPresenter = createPresenter();
        mPresenter.attach(this);

        mMvpProxy = new ActivityMvpProxyImpl<>(this);
        mMvpProxy.bindAndCreatePresenter();

        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
        mMvpProxy.unbindPresenter();
    }

    protected abstract void setContentView();

    /**
     * 由子类去实现创建
     *
     * @return
     */
    protected abstract P createPresenter();

    protected abstract void initView();

    protected abstract void initData();

    public P getPresenter() {
        return mPresenter;
    }
}