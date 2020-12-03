package com.nan.day24_pattern_state.simple2.state;

/**
 * 待付款状态
 */
public class ObligationState implements IOrderState {

    @Override
    public boolean pay() {
        System.out.println("付款");
        return true;
    }

    @Override
    public boolean deliver() {
        System.out.println("未付款不能发货");
        return false;
    }

}
