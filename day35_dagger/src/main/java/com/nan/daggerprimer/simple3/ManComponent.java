package com.nan.daggerprimer.simple3;

import dagger.Component;

@Component(modules = {CarModule.class})
public interface ManComponent {

    void injectMan(Man man);

}
