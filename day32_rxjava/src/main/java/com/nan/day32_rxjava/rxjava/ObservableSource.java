package com.nan.day32_rxjava.rxjava;

public interface ObservableSource<T> {
    void subscribe(Observer<T> observer);
}
