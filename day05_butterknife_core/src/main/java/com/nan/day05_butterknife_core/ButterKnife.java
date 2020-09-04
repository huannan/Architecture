package com.nan.day05_butterknife_core;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.lang.reflect.Constructor;

public class ButterKnife {

    public static Unbinder bind(@NonNull Activity target) {
        Unbinder unbinder = Unbinder.EMPTY;
        try {
            Class<? extends Unbinder> bindingClass = (Class<? extends Unbinder>) Class.forName(target.getClass().getName() + "_ViewBinding");
            Constructor<? extends Unbinder> bindingConstructor = bindingClass.getConstructor(target.getClass());
            unbinder = bindingConstructor.newInstance(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unbinder;
    }

}
