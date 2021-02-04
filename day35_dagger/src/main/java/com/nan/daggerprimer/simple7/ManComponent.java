package com.nan.daggerprimer.simple7;

import com.nan.daggerprimer.simple5.CarModule;
import com.nan.daggerprimer.simple5.Man;

import dagger.Component;

@Component(modules = {CarModule.class})
public interface ManComponent {

    void injectMan(Man man);

}
