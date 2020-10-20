package com.nan.day13_pattern_strategy.simple1;

public class Client {
    public static void main(String[] args) {
        FinanceManager financeManager = new FinanceManager();
        float money = financeManager.finance(3, 10000, FinanceManager.Finance.ZHI_FU_BAO);
        System.out.println("money = " + money);
    }
}
