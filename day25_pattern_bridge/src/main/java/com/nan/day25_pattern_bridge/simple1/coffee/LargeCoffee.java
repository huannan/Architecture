package com.nan.day25_pattern_bridge.simple1.coffee;

import com.nan.day25_pattern_bridge.simple1.additives.ICoffeeAdditives;

public class LargeCoffee extends Coffee {
    public LargeCoffee(ICoffeeAdditives additives) {
        super(additives);
    }

    @Override
    public void makeCoffee() {
        System.out.println("大杯的" + mAdditives + "咖啡");
    }
}
