package com.nan.day32_rxjava.rxjava;

public interface Observer<T> {

    void onSubscribe();

    void onNext(T t);

    void onError(Throwable e);

    void onComplete();

}
