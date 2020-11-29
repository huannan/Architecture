package com.nan.day16_pattern_proxy.simple2;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        Man man = new Man("huannan");

        // 返回的是IBank的实现类,由Java JNI实现
        IBank bank = (IBank) Proxy.newProxyInstance(
                // ClassLoader
                IBank.class.getClassLoader(),
                // 目标接口
                new Class<?>[]{IBank.class},
                // 关键
                new BankInvocationHandler(man));

        // 这里会去invoke方法
        bank.applyBank();
    }
}
