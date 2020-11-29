package com.nan.day16_pattern_proxy.simple2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 银行办理业务 - 动态代理
 * IBank变更的时候,不需要再修改代理对象
 */
public class BankInvocationHandler implements InvocationHandler {

    /**
     * 被代理对象
     */
    private Man mMan;

    public BankInvocationHandler(Man man) {
        mMan = man;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 执行方法,目标接口调用的方法都会来到这里
        // System.out.println(method.getName() + " " + args);
        System.out.println("开始受理");
        Object result = method.invoke(mMan, args);
        System.out.println("操作完毕");
        return result;
    }
}
