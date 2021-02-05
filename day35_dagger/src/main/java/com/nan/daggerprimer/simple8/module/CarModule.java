package com.nan.daggerprimer.simple8.module;

import com.nan.daggerprimer.simple8.Car;
import com.nan.daggerprimer.simple8.component.SonComponent;

import dagger.Module;
import dagger.Provides;

/**
 * 怎么表明一个 SubComponent 是属于哪个 parent Component 的呢？只需要在 parent Component 依赖的 Module 中的subcomponents加上 SubComponent 的 class
 * 然后就可以在 parent Component 中请求 SubComponent.Builder
 */
@Module(subcomponents = {SonComponent.class})
public class CarModule {

    @Provides
    public Car provideCar() {
        System.out.println("provideCar invoke");
        return new Car();
    }

}
