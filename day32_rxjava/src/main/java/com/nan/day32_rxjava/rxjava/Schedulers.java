package com.nan.day32_rxjava.rxjava;

import android.os.Handler;
import android.os.Looper;

public abstract class Schedulers {

    private static final Scheduler IO;
    private static final Scheduler MAIN_THREAD;

    static {
        IO = new Scheduler.IoScheduler();
        MAIN_THREAD = new Scheduler.HandlerScheduler(new Handler(Looper.getMainLooper()));
    }

    public static Scheduler io() {
        return IO;
    }

    public static Scheduler mainThread() {
        return MAIN_THREAD;
    }

}
