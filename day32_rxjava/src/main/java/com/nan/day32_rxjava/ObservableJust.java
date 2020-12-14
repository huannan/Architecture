package com.nan.day32_rxjava;

public class ObservableJust<T> extends Observable<T> {

    private T item;

    public ObservableJust(T item) {
        this.item = item;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        observer.onSubscribe();
        try {
            observer.onNext(item);
        } catch (Exception e) {
            observer.onError(e);
        }
        observer.onComplete();
    }
}
