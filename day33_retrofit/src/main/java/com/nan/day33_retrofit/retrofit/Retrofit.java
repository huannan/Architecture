package com.nan.day33_retrofit.retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.OkHttpClient;

public class Retrofit {

    final String baseUrl;
    final OkHttpClient okHttpClient;
    private Map<Method, ServiceMethod> mServiceMethodMap = new ConcurrentHashMap<>();

    private Retrofit(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.okHttpClient = builder.okHttpClient;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> service) {
        // 检验是不是一个接口,而且不能继承

        // 生成动态代理
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 每执行一个方法都会来这里

                // 判断是不是Object的方法
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, args);
                }

                // 解析参数注解
                ServiceMethod serviceMethod = loadServiceMethod(method);

                // 封装OhHttpCall然后返回,最后代理Call,中间插入一些流程,比较返回的工厂处理

                return null;
            }
        });
    }

    private ServiceMethod loadServiceMethod(Method method) {
        // 享元设计模式
        ServiceMethod serviceMethod = mServiceMethodMap.get(method);
        if (null == serviceMethod) {
            serviceMethod = new ServiceMethod(this, method);
        }
        return serviceMethod;
    }

    public static final class Builder {
        String baseUrl;
        OkHttpClient okHttpClient;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public void client(OkHttpClient client) {
            this.okHttpClient = client;
        }

        public Retrofit build() {
            return new Retrofit(this);
        }

    }

}
