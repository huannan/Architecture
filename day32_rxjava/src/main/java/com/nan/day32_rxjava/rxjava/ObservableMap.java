package com.nan.day32_rxjava.rxjava;

public class ObservableMap<T, U> extends Observable<U> {

    final ObservableSource<T> source;
    final Function<T, U> function;

    public ObservableMap(ObservableSource<T> source, Function<T, U> function) {
        this.source = source;
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<U> observer) {
        source.subscribe(new MapObserver<>(observer, function));
    }

    private static final class MapObserver<T, U> implements Observer<T> {

        final Observer<U> observe;
        final Function<T, U> function;

        public MapObserver(Observer<U> observer, Function<T, U> function) {
            this.observe = observer;
            this.function = function;
        }

        @Override
        public void onSubscribe() {
            observe.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            U apply = function.apply(t);
            observe.onNext(apply);
        }

        @Override
        public void onError(Throwable e) {
            observe.onError(e);
        }

        @Override
        public void onComplete() {
            observe.onComplete();
        }
    }
}
