package com.nan.day11_pattern_decorator.simple2;

public class TeacherEat implements Eat {

    private Eat mEat;

    public TeacherEat(PersonEat eat) {
        mEat = eat;
    }

    @Override
    public void eat() {
        System.out.println("点菜");
        System.out.println("喝汤");
        mEat.eat();
        System.out.println("吃完走人");
    }
}
