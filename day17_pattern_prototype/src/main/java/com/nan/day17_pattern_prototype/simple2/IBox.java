package com.nan.day17_pattern_prototype.simple2;

/**
 * 出货的箱子接口
 */
public interface IBox {
    void setNumber(int number);// 设置箱子的数量

    int getNumber();// 有多少货

    /**
     * ç
     */
    IBox copy();
}
