package com.nan.day24_pattern_state.simple2.state;

/**
 * 订单的操作
 */
public interface IOrderState {

    /**
     * 付款
     */
    boolean pay();

    /**
     * 发货
     */
    boolean deliver();
}
