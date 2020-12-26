package com.nan.day34_mvp.simple5.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nan.day34_mvp.simple5.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    private P mPresenter;
    private List<BasePresenter> mCustomPresenters;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        // 创建 P，创建只能交给 子类，每个 Activity 都不一样
        mPresenter = createPresenter();
        mPresenter.attach(this);

        mCustomPresenters = new ArrayList<>();

        // 这里只是放在了Activity,Fragment和ViewGroup也需要

        // 注入用户自己的Presenter,这里也可以用Dagger
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if (null != injectPresenter) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                try {
                    BasePresenter presenter = (BasePresenter) type.newInstance();
                    field.set(this, presenter);

                    presenter.attach(this);
                    mCustomPresenters.add(presenter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
        for (BasePresenter presenter : mCustomPresenters) {
            presenter.detach();
        }
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