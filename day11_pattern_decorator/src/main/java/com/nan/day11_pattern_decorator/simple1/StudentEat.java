package com.nan.day11_pattern_decorator.simple1;

public class StudentEat implements Eat {
    @Override
    public void eat() {
        System.out.println("点菜");
        System.out.println("人吃饭吃菜");
        System.out.println("收拾盘子");
    }
}
