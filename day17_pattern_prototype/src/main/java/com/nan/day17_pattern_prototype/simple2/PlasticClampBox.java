package com.nan.day17_pattern_prototype.simple2;

/**
 * 具体的出货的物品 - 塑料夹子
 */
public class PlasticClampBox implements IBox {
    private int number;
    private String name;

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IBox copy() {
        PlasticClampBox box = new PlasticClampBox();
        box.setNumber(number);
        box.setName(name);
        return box;
    }
}