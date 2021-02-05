package com.nan.daggerprimer.simple8;

import com.nan.daggerprimer.simple8.component.DaggerManComponent;
import com.nan.daggerprimer.simple8.component.ManComponent;
import com.nan.daggerprimer.simple8.component.SonComponent;

import javax.inject.Inject;

public class Son {

    @Inject
    Car car;

    @Inject
    Bike bike;

    public static void main(String[] args) {
        ManComponent manComponent = DaggerManComponent.builder()
                .build();
        SonComponent sonComponent = manComponent.sonComponent()
                .build();

        Man man = new Man();
        Son son = new Son();

        manComponent.injectMan(man);
        sonComponent.injectSon(son);

        man.car.run();
        son.car.run();
        son.bike.run();
    }

}
