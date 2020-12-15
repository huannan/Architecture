package com.nan.day32_rxjava;

public abstract class Observable<T> implements ObservableSource<T> {

    public static <T> Observable<T> just(T item) {
        return new ObservableJust<>(item);
    }

    public <U> Observable<U> map(Function<T, U> function) {
        return new ObservableMap<>(this, function);
    }

    public Observable<T> subscribeOn(Scheduler scheduler) {
        return new ObservableSubscribeOn<T>(this, scheduler);
    }

    public Observable<T> observerOn(Scheduler scheduler) {
        return new ObservableObserverOn<>(this,scheduler);
    }

    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    public void subscribe(Consumer<T> consumer) {
        subscribe(new LambdaObserver<T>(null, consumer, null, null));
    }

    protected abstract void subscribeActual(Observer<T> observer);

    private static final class LambdaObserver<T> implements Observer<T> {

        final Action onSubscribe;
        final Consumer<T> onNext;
        final Consumer<Throwable> onError;
        final Action onComplete;

        public LambdaObserver(Action onSubscribe, Consumer<T> onNext, Consumer<Throwable> onError, Action onComplete) {
            this.onSubscribe = onSubscribe;
            this.onNext = onNext;
            this.onError = onError;
            this.onComplete = onComplete;
        }

        @Override
        public void onSubscribe() {
            if (null != onSubscribe) {
                onSubscribe.run();
            }
        }

        @Override
        public void onNext(T t) {
            onNext.accept(t);
        }

        @Override
        public void onError(Throwable e) {
            if (null != onError) {
                onError.accept(e);
            }
        }

        @Override
        public void onComplete() {
            if (null != onComplete) {
                onComplete.run();
            }
        }
    }

}
