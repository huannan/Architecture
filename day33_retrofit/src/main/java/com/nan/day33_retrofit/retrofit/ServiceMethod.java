package com.nan.day33_retrofit.retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ServiceMethod {

    public ServiceMethod(Retrofit retrofit, Method method) {
        // 解析方法上面的注解
        Annotation[] methodAnnotations = method.getAnnotations();
        for (Annotation methodAnnotation : methodAnnotations) {

        }

        // 解析参数上面的注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (Annotation[] parameterAnnotation : parameterAnnotations) {

        }
    }
}