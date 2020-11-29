package com.nan.day17_pattern_prototype.simple3;

/**
 * 具体的出货的物品 - 汽车的零件
 */
public class CarPartBox extends IBox {
    private int number;
    private String name;
    private String carBrand;// 汽车的品牌

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

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
}
