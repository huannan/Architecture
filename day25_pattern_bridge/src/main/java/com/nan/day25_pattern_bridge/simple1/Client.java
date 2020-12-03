package com.nan.day25_pattern_bridge.simple1;

import com.nan.day25_pattern_bridge.simple1.additives.Original;
import com.nan.day25_pattern_bridge.simple1.additives.Sugar;
import com.nan.day25_pattern_bridge.simple1.coffee.LargeCoffee;
import com.nan.day25_pattern_bridge.simple1.coffee.MiddleCoffee;
import com.nan.day25_pattern_bridge.simple1.coffee.SmallCoffee;

public class Client {

    public static void main(String[] args) {
        Sugar sugar = new Sugar();
        SmallCoffee smallCoffee = new SmallCoffee(sugar);
        smallCoffee.makeCoffee();

        Original original = new Original();
        LargeCoffee largeCoffee = new LargeCoffee(original);
        largeCoffee.makeCoffee();

        MiddleCoffee middleCoffee = new MiddleCoffee(original);
        middleCoffee.makeCoffee();
    }

}
