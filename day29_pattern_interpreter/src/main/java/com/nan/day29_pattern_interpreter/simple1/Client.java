package com.nan.day29_pattern_interpreter.simple1;

import java.util.HashMap;

public class Client {

    public static void main(String[] args) throws Exception {
        //获取表达式
        String expStr = Calculator.getExpStr();
        //为表达式中的元素赋值，这里的HashMap充当了Context上下文角色
        HashMap<String, Integer> values = Calculator.getValues(expStr);
        //构造Calculator，解释表达式
        Calculator calculator = new Calculator(expStr);
        //运算
        int result = calculator.calculate(values);

        System.out.println("计算结果为：" + result);
    }
}