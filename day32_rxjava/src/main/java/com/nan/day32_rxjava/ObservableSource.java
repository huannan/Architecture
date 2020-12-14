package com.nan.day32_rxjava;

import com.nan.day32_rxjava.sample.Observer;

public interface ObservableSource<T> {
    void subscribe(Observer<T> observer);
}
