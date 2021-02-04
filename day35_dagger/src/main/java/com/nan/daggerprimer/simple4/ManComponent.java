package com.nan.daggerprimer.simple4;

import dagger.Component;

@Component(modules = {CarModule.class})
public interface ManComponent {

    void injectMan(Man man);

}
