package com.nan.daggerprimer.simple3;

import javax.inject.Inject;

public class Car {

    @Inject
    public Car() {
        System.out.println("Car constructor invoke");
    }

    public void run() {
        System.out.println(this + " 汽车发动");
    }

}
