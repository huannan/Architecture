package com.nan.day31_okhttp.simple2;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({
        Method.GET,
        Method.POST,
        Method.PUT,
        Method.DELETE,
        Method.PATCH
})
@Retention(RetentionPolicy.SOURCE)
public @interface Method {
    String GET = "GET";
    String POST = "POST";
    String PUT = "PUT";
    String DELETE = "DELETE";
    String PATCH = "PATCH";
}
