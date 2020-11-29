package com.nan.day17_pattern_prototype.simple3;

/**
 * 装箱子的卡车
 */
public class TruckCar {
    public IBox box;

    public void addBox(IBox box) {
        this.box = box;
    }

    public IBox remove() {
        return box;
    }
}
