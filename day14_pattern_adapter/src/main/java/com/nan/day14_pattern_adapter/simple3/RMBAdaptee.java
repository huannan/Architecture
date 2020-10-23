package com.nan.day14_pattern_adapter.simple3;

/**
 * 代表第一个版本的人民币
 */
public class RMBAdaptee {
    private float mRmb;

    public RMBAdaptee(float rmb) {
        this.mRmb = rmb;
    }

    /**
     * 获取人民币
     */
    public float getRmb() {
        return mRmb;
    }

}
