package com.nan.daggerprimer.simple7;

import com.nan.daggerprimer.simple5.Car;

import javax.inject.Inject;

public class Man {

    @Inject
    Car car;

    public Man() {

    }

}