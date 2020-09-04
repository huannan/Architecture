package com.nan.day01_principle.simple4.http;

public abstract class HttpCallBack<T>{

    // 返回可以直接操作的对象
    public abstract void onSuccess(T result);

    public abstract void onFailure(Exception e);
}