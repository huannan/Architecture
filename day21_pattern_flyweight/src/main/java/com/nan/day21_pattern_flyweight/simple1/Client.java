package com.nan.day21_pattern_flyweight.simple1;

public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Ticket ticket = TicketFactory.getTicket("深圳", "长沙");
        }
    }
}
