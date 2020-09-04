package com.nan.day05_butterknife_core;

import android.support.annotation.UiThread;

public interface Unbinder {

    @UiThread
    void unbind();

    Unbinder EMPTY = new Unbinder() {
        @Override public void unbind() { }
    };

}
