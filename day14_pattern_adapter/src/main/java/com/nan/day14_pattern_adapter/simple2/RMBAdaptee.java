package com.nan.day14_pattern_adapter.simple2;

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

    public float getUsd() {
        return mRmb / 5.6f;
    }
}
