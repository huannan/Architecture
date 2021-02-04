package com.nan.daggerprimer.simple5;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public class CarModule {

    // @MyScope
    // @Singleton
    @Reusable
    @Provides
    public Car provideCar() {
        System.out.println("provideCar invoke");
        return new Car();
    }

}
