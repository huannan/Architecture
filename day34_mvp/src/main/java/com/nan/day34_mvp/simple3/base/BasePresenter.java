package com.nan.day34_mvp.simple3.base;

public class BasePresenter<V extends BaseView> {

    // 目前两个两个公用方法 ，传递的时候 会有不同的 View ，怎么办？-> 用泛型
    // View有一个特点,都是接口
    private V mView;

    public void attach(V view) {
        this.mView = view;
    }

    public void detach() {
        // 不解绑的问题 Activity -> Presenter  ,Presenter 持有了 Activity
        // 应该是会有内存泄漏
        this.mView = null;
    }

    public V getView() {
        return mView;
    }

}
