package com.nan.day25_pattern_bridge.simple1.coffee;

import com.nan.day25_pattern_bridge.simple1.additives.ICoffeeAdditives;

public class SmallCoffee extends Coffee {
    public SmallCoffee(ICoffeeAdditives additives) {
        super(additives);
    }

    @Override
    public void makeCoffee() {
        System.out.println("小杯的" + mAdditives + "咖啡");
    }
}
