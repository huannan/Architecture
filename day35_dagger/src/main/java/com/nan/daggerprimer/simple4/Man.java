package com.nan.daggerprimer.simple4;

import com.nan.daggerprimer.simple4.car.Car;

import javax.inject.Inject;
import javax.inject.Named;

public class Man {

    @Inject
    @Named("car2")
    Car car;

    public Man() {
        DaggerManComponent.create().injectMan(this);
    }

    public static void main(String[] args) {
        Man man = new Man();
        man.car.run();
    }
}