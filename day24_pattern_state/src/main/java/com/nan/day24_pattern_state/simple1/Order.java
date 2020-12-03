package com.nan.day24_pattern_state.simple1;

/**
 * 订单
 * 3种状态 第一个版本其实够了，但是第二个版本增加了很多状态
 */
public class Order {

    public final int OBLIGATION = 1;// 代付款
    public final int PAID = 2;// 付款
    public final int WAIT_RECEIVING = 3;// 待收货
    public final int WAIT_COMMENT = 4;// 待评价

    // 订单状态
    public int mState = OBLIGATION;

    /**
     * 付款
     */
    public void pay() {
        if (mState == OBLIGATION) {
            mState = PAID;
            System.out.println("付款");
        } else {
            System.out.println("不在状态");
        }
    }

    /**
     * 发货
     */
    public void deliver() {
        if (mState == PAID) {
            mState = WAIT_RECEIVING;
            System.out.println("发货");
        } else {
            System.out.println("不在状态");
        }
    }
}
