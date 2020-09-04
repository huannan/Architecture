package com.nan.day05_butterknife_core;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;

public class Utils {

    public static <T extends View> T findViewById(Activity target, @IdRes int id) {
        return target.findViewById(id);
    }

}
