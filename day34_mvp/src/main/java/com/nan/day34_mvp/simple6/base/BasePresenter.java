package com.nan.day34_mvp.simple6.base;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class BasePresenter<V extends BaseView, M extends BaseModel> {

    // 目前两个两个公用方法 ，传递的时候 会有不同的 View ，怎么办？-> 用泛型
    // View有一个特点,都是接口
    private WeakReference<V> mView;
    private V mViewProxy;
    private M mModel;

    @SuppressWarnings("unchecked")
    public void attach(V view) {
        this.mView = new WeakReference<>(view);
        /**
         * 解决办法:利用AOP思想(AspectJ/动态代理)将判空统一处理
         * 思考:虽然已经统一处理了判空,但是感觉还是没有把泄漏问题解决,继续把mView改成弱引用
         */
        mViewProxy = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new ViewInvocationHandler<>(view));

        // 动态创建Model,不需要使用者手动创建
        // 创建我们的 Model ，动态创建？ 获取 Class 通过反射 （Activity实例创建的？class 反射创建的，布局的 View 对象怎么创建的？反射）
        // 获取 Class 对象
        try {
            // 最好要判断一下类型
            Type[] params = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
            mModel = (M) ((Class<?>) params[1]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detach() {
        // 不解绑的问题 Activity -> Presenter  ,Presenter 持有了 Activity
        // 应该是会有内存泄漏
        this.mView = null;
    }

    /**
     * 每次使用View之前都判断一下,很麻烦
     * 解决办法:利用AOP思想(AspectJ/动态代理)将判空统一处理
     */
    public V getView() {
        return mViewProxy;
    }

    public M getModel() {
        return mModel;
    }

    private static final class ViewInvocationHandler<V> implements InvocationHandler {

        private WeakReference<V> mView;

        public ViewInvocationHandler(V view) {
            mView = new WeakReference<>(view);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (null == mView || null == mView.get()) {
                return null;
            }
            // 被代理对象的方法
            return method.invoke(mView.get(), args);
        }
    }

}
