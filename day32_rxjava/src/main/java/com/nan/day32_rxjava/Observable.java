package com.nan.day32_rxjava;

public abstract class Observable<T> implements ObservableSource<T> {

    public static <T> ObservableSource<T> just(T item) {
        return null;
    }

}
