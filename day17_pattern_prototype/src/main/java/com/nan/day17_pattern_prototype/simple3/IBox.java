package com.nan.day17_pattern_prototype.simple3;

import android.support.annotation.NonNull;

/**
 * 出货的箱子接口
 */
public abstract class IBox implements Cloneable {
    abstract void setNumber(int number);// 设置箱子的数量

    abstract int getNumber();// 有多少货

    /**
     * 利用系统的拷贝方法
     */
    @NonNull
    @Override
    protected IBox clone() throws CloneNotSupportedException {
        return (IBox) super.clone();
    }
}
