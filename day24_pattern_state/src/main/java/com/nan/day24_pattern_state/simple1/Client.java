package com.nan.day24_pattern_state.simple1;

public class Client {

    public static void main(String[] args){
        Order order = new Order();
        order.pay();
        order.deliver();
    }

}
