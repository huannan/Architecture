package com.nan.day24_pattern_state.simple2;

import com.nan.day24_pattern_state.simple2.state.IOrderState;
import com.nan.day24_pattern_state.simple2.state.ObligationState;
import com.nan.day24_pattern_state.simple2.state.PaidState;
import com.nan.day24_pattern_state.simple2.state.WaitReceivingState;

/**
 * 订单
 */
public class Order implements IOrderState {

    // 订单状态
    public IOrderState mState;

    public Order() {
        mState = new ObligationState();
    }

    @Override
    public boolean pay() {
        boolean success = mState.pay();
        if (success) {
            mState = new PaidState();
        }
        return success;
    }

    @Override
    public boolean deliver() {
        boolean success = mState.deliver();
        if (success) {
            mState = new WaitReceivingState();
        }
        return success;
    }
}
