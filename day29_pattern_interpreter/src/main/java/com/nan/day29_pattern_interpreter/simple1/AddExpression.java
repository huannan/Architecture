package com.nan.day29_pattern_interpreter.simple1;

import java.util.HashMap;

/**
 * 加法
 */
public class AddExpression extends SymbolExpression{

    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return this.mLeft.interpreter(var) + this.mRight.interpreter(var);
    }
}