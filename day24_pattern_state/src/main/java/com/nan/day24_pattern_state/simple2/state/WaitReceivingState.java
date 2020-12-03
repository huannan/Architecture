package com.nan.day24_pattern_state.simple2.state;

/**
 * 待收货状态
 */
public class WaitReceivingState implements IOrderState {

    @Override
    public boolean pay() {
        System.out.println("待收货，不能重复付款");
        return false;
    }

    @Override
    public boolean deliver() {
        System.out.println("已发货，不能重复发货");
        return false;
    }

}
