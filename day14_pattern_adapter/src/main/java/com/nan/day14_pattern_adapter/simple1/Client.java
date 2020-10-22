package com.nan.day14_pattern_adapter.simple1;

public class Client {
    public static void main(String[] args) {
        RMBAdaptee adaptee = new RMBAdaptee(100F);
        float usd = adaptee.getUsd();
        System.out.println(usd);
    }
}
