package com.nan.day14_pattern_adapter.simple2;

public class Client {
    public static void main(String[] args) {
        // 角色：需要适配的接口 Target (美元) ，适配器对象 （Adapter）
        // 即RMBAdaptee -> USDTarget
        USDAdapter adapter = new USDAdapter(100F);
        float usd = adapter.getUsd();
        System.out.println(usd);
    }
}
