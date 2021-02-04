package com.nan.daggerprimer.simple3;

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
