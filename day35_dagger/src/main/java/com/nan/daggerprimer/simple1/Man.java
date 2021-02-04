package com.nan.daggerprimer.simple1;

import javax.inject.Inject;

public class Man {

    /**
     * 可以标注属性
     */
    @Inject
    Car car;

    Car car1;

    public Man() {
        DaggerManComponent.create().injectMan(this);
    }

    /**
     * 可以标注方法
     */
    @Inject
    public void setCar1(Car car1) {
        this.car1 = car1;
    }

    public static void main(String[] args) {
        Man man = new Man();
        man.car.run();

        man.car1.run();
    }
}