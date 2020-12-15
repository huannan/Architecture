package com.nan.day32_rxjava;

public class ObservableSubscribeOn<T> extends Observable<T> {

    final ObservableSource<T> source;
    final Scheduler scheduler;

    public ObservableSubscribeOn(ObservableSource<T> source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        scheduler.scheduleDirect(new SchedulerTask<>(source, observer));
    }

    private static final class SchedulerTask<T> implements Runnable {

        final ObservableSource<T> source;
        final Observer<T> observer;

        public SchedulerTask(ObservableSource<T> source, Observer<T> observer) {
            this.source = source;
            this.observer = observer;
        }

        @Override
        public void run() {
            source.subscribe(observer);
        }
    }

}
