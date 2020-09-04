package com.nan.day06_apt_pay_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface WXPayEntry {

    String packageName();

    Class<?> baseEntryActivity();

}
