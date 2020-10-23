package com.nan.day14_pattern_adapter.simple2;

/**
 * 适配器对象 - 把人民币转成美元
 * 类适配，通过继承
 */
public class USDAdapter extends RMBAdaptee implements USDTarget {
    public USDAdapter(float rmb) {
        super(rmb);
    }

    @Override
    public float getUsd() {
        return getRmb() / 5.6f;
    }
}
