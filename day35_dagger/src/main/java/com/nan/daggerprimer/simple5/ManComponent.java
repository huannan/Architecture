package com.nan.daggerprimer.simple5;

import javax.inject.Singleton;

import dagger.Component;

// @MyScope
// @Singleton
@Component(modules = {CarModule.class})
public interface ManComponent {

    void injectMan(Man man);

}
