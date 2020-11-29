package com.nan.day16_pattern_proxy.simple1;

public class Client {
    public static void main(String[] args) {
        Man man = new Man("huannan");
        BankWorker bankWorker = new BankWorker(man);
        bankWorker.applyBank();
    }
}
