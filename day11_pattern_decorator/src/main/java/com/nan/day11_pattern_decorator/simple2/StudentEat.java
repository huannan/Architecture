package com.nan.day11_pattern_decorator.simple2;

public class StudentEat implements Eat {

    private Eat mEat;

    public StudentEat(PersonEat eat) {
        mEat = eat;
    }

    @Override
    public void eat() {
        System.out.println("点菜");
        mEat.eat();
        System.out.println("收拾盘子");
    }
}
