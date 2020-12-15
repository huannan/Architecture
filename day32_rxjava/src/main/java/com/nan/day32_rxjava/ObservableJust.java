package com.nan.day32_rxjava;

public class ObservableJust<T> extends Observable<T> {

    private T item;

    public ObservableJust(T item) {
        this.item = item;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        // ScalarDisposable是Observer静态代理,方便扩展
        ScalarDisposable<T> sd = new ScalarDisposable<>(observer, item);
        observer.onSubscribe();
        sd.run();
    }

    private static final class ScalarDisposable<T> implements Runnable{

        final Observer< T> observer;
        final T value;

        public ScalarDisposable(Observer< T> observer, T value) {
            this.observer = observer;
            this.value = value;
        }

        @Override
        public void run() {
            try {
                observer.onNext(value);
            } catch (Exception e) {
                observer.onError(e);
            }
            observer.onComplete();
        }
    }

}
