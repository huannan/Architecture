package com.nan.day32_rxjava;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public abstract class Scheduler {

    public abstract void scheduleDirect(Runnable runnable);

    public static final class IoScheduler extends Scheduler {

        ExecutorService service;

        public IoScheduler(){
            service = Executors.newScheduledThreadPool(1, new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    return new Thread(r);
                }
            });
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            service.submit(runnable);
        }
    }

    public static final class HandlerScheduler extends Scheduler {

        private Handler handler;

        public HandlerScheduler(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            Message message = Message.obtain(handler, runnable);
            handler.sendMessage(message);
        }
    }

}
