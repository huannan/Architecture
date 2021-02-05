package com.nan.daggerprimer.simple7.module;

import com.nan.daggerprimer.simple7.Car;

import dagger.Module;
import dagger.Provides;

@Module
public class CarModule {

    @Provides
    public Car provideCar() {
        System.out.println("provideCar invoke");
        return new Car();
    }

}
