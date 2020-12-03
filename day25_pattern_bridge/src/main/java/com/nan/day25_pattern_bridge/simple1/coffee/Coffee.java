package com.nan.day25_pattern_bridge.simple1.coffee;

import com.nan.day25_pattern_bridge.simple1.additives.ICoffeeAdditives;

/**
 * 咖啡 大杯，小杯 抽象 加料
 */
public abstract class Coffee {
    protected ICoffeeAdditives mAdditives;

    public Coffee(ICoffeeAdditives additives) {
        this.mAdditives = additives;
    }

    // 生成一杯咖啡
    public abstract void makeCoffee();

}
