package com.nan.daggerprimer.simple7;

import com.nan.daggerprimer.simple5.Car;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public class CarModule {

    @Provides
    public Car provideCar() {
        System.out.println("provideCar invoke");
        return new Car();
    }

}
