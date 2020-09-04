package com.nan.day04_java_basics;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class ViewUtils {

    public static void inject(Activity owner) {
        Field[] fields = owner.getClass().getDeclaredFields();
        for (Field field : fields) {
            ViewById viewById = field.getAnnotation(ViewById.class);
            if (null != viewById) {
                View view = owner.findViewById(viewById.value());
                field.setAccessible(true);
                try {
                    field.set(owner, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
