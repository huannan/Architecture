package com.nan.day02_aop.aop;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.nan.day02_aop.util.Utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class SectionAspect {

    private static final String TAG = "SectionAspect";

    /**
     * 找到处理的切点
     */
    @Pointcut(value = "execution(@com.nan.day02_aop.aop.CheckNet * *(..))")
    public void checkNetBehavior() {

    }

    /**
     * 处理切面
     */
    @Around("checkNetBehavior()")
    public Object checkNet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 日志、埋点、上传、权限监测
        Log.e(TAG, "checkNet");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckNet checkNet = signature.getMethod().getAnnotation(CheckNet.class);
        if (null != checkNet) {
            // Activity View Fragment 当前切点所在的类
            Object object = joinPoint.getThis();
            Context context = Utils.getContext(object);
            if (null != context && !Utils.isNetworkAvailable(context)) {
                Toast.makeText(context, "没有网络不能跳转", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        return joinPoint.proceed();
    }

}
