package com.nan.day24_pattern_state.simple2.state;

/**
 * 待评价状态
 */
public class WaitCommentState implements IOrderState {

    @Override
    public boolean pay() {
        return false;
    }

    @Override
    public boolean deliver() {
        return false;
    }

}
