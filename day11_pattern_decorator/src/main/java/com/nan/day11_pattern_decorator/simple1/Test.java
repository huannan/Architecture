package com.nan.day11_pattern_decorator.simple1;

public class Test {
    public static void main(String[] args) {
        // 类功能的扩展(越来越强大): 一般的写法，通过继承的方式实现
        Eat eat = new TeacherEat();
        eat.eat();
    }
}
