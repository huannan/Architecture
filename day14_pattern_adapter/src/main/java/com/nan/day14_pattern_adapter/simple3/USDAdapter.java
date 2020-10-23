package com.nan.day14_pattern_adapter.simple3;

/**
 * 适配器对象 - 把人民币转成美元
 * 对象适配，传递实例进来，没有继承
 */
public class USDAdapter implements USDTarget {

    private RMBAdaptee mAdaptee;

    public USDAdapter(RMBAdaptee adaptee) {
        mAdaptee = adaptee;
    }

    @Override
    public float getUsd() {
        return mAdaptee.getRmb() / 5.6f;
    }
}
