package com.nan.day32_rxjava;

public abstract class Observable<T> implements ObservableSource<T> {

    public static <T> ObservableSource<T> just(T item) {
        return new ObservableJust<>(item);
    }

    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);
}
