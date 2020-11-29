package com.nan.day16_pattern_proxy.simple1;

/**
 * 银行办理业务 - 目标接口（业务）
 */
public interface IBank {
    /**
     * 申请办卡
     */
    void applyBank();

    /**
     * 挂失
     */
    void lostBank();
}
