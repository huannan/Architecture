package com.nan.daggerprimer.simple6;

import dagger.BindsInstance;
import dagger.Component;

@Component
public interface ManComponent {

    void injectMan(Man man);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder car(Car car);

        ManComponent build();

    }

}
