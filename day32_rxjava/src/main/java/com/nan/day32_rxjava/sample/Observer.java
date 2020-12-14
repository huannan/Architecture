package com.nan.day32_rxjava.sample;

import io.reactivex.disposables.Disposable;

public interface Observer<T> {

    void onSubscribe(Disposable d);

    void onNext(T t);

    void onError(Throwable e);

    void onComplete();

}
