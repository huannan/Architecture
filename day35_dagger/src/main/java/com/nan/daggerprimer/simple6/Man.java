package com.nan.daggerprimer.simple6;

import javax.inject.Inject;

public class Man {

    @Inject
    Car car;

    public Man() {
        // 依赖实例与Component强制绑定
        ManComponent manComponent = DaggerManComponent
                .builder()
                .car(new Car())
                .build();
        manComponent.injectMan(this);
    }

    public static void main(String[] args) {
        Man man = new Man();
        man.car.run();
    }
}