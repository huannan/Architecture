package com.nan.daggerprimer.simple8.module;

import com.nan.daggerprimer.simple8.Bike;

import dagger.Module;
import dagger.Provides;

@Module
public class BikeModule {

    @Provides
    public Bike provideBike() {
        System.out.println("provideBike invoke");
        return new Bike();
    }

}
