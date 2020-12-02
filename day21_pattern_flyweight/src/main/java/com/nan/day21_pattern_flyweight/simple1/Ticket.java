package com.nan.day21_pattern_flyweight.simple1;

import java.util.Random;

/**
 * 火车票
 */
public class Ticket {
    String from;
    String to;

    public Ticket(String form, String to) {
        this.from = form;
        this.to = to;
    }

    int getPrice() {
        return new Random().nextInt(100) + 20;
    }
}
