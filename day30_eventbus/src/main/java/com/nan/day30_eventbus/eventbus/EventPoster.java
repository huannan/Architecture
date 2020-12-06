package com.nan.day30_eventbus.eventbus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EventPoster {

    static volatile EventPoster defaultInstance;
    private final Executor threadPool;

    private EventPoster() {
        this.threadPool = Executors.newCachedThreadPool();
    }

    public static EventPoster getDefault() {
        if (defaultInstance == null) {
            synchronized (EventPoster.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventPoster();
                }
            }
        }
        return defaultInstance;
    }

    public static void enqueue(Object event, Subscription subscription) {
        getDefault().threadPool.execute(new MethodInvokeRunnable(event, subscription));
    }

    private static final class MethodInvokeRunnable implements Runnable {
        private Object event;
        private Subscription subscription;

        public MethodInvokeRunnable(Object event, Subscription subscription) {
            this.event = event;
            this.subscription = subscription;
        }

        @Override
        public void run() {
            EventBus.getDefault().invokeMethodInternal(event, subscription);
        }
    }

}
