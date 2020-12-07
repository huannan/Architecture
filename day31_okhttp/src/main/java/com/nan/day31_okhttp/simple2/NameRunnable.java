package com.nan.day31_okhttp.simple2;

public abstract class NameRunnable implements Runnable {

    @Override
    public final void run() {
        execute();
    }

    public abstract void execute();
}
