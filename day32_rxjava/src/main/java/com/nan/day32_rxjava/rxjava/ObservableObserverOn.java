package com.nan.day32_rxjava.rxjava;

public class ObservableObserverOn<T> extends Observable<T> {

    final ObservableSource<T> source;
    final Scheduler scheduler;

    public ObservableObserverOn(ObservableSource<T> source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        source.subscribe(new ObserverOnObserver<>(observer, scheduler));
    }

    private static final class ObserverOnObserver<T> implements Observer<T>, Runnable {

        final Observer<T> observer;
        final Scheduler scheduler;
        T t;

        public ObserverOnObserver(Observer<T> observer, Scheduler scheduler) {
            this.observer = observer;
            this.scheduler = scheduler;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            this.t = t;
            scheduler.scheduleDirect(this);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void run() {
            observer.onNext(t);
        }
    }

}
