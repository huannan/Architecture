package com.nan.daggerprimer.simple2;

import javax.inject.Inject;

public class Man {

    @Inject
    Car car;

    public Man() {
        DaggerManComponent.create().injectMan(this);
    }

    public static void main(String[] args) {
        Man man = new Man();
        man.car.run();
    }
}