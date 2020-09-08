package com.nan.day11_pattern_decorator.simple1;

public class PersonEat implements Eat {
    @Override
    public void eat() {
        System.out.println("人吃饭吃菜");
    }
}
