package com.nan.day11_pattern_decorator.simple2;

public class Test {
    public static void main(String[] args) {
        // 类功能的扩展(越来越强大): 使用装饰设计模式
        // 装饰设计模式的套路:一般情况下都是把类对象作为构造参数传递进去
        Eat eat = new TeacherEat(new PersonEat());
        eat.eat();
    }
}
