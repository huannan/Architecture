package com.nan.daggerprimer.simple8.component;

import com.nan.daggerprimer.simple8.Man;
import com.nan.daggerprimer.simple8.module.CarModule;
import com.nan.daggerprimer.simple8.scope.ManScope;

import dagger.Component;

@ManScope
@Component(modules = {CarModule.class})
public interface ManComponent {

    void injectMan(Man man);

    /**
     * 用来创建 Subcomponent
     */
    SonComponent.Builder sonComponent();

}
