package com.nan.day13_pattern_strategy.simple2;

public class Client {
    public static void main(String[] args) {
        // 这个就是采用了我们的策略设计模式，说白了就是在原来的基础上进行了分离
        IFinance finance = new ZhifubaoFinance();
        // 上下文
        FinanceContext context = new FinanceContext(finance);
        float money = context.finance(3, 10000);
        System.out.println("money = " + money);
        // 跟工厂设计模式很像
    }
}
