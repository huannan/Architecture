package com.nan.daggerprimer.simple7.component;

import com.nan.daggerprimer.simple7.Car;
import com.nan.daggerprimer.simple7.module.CarModule;
import com.nan.daggerprimer.simple7.Man;
import com.nan.daggerprimer.simple7.scope.ManScope;

import dagger.Component;

@ManScope
@Component(modules = {CarModule.class})
public interface ManComponent {

    void injectMan(Man man);

    /**
     * 必须向外提供 car 依赖实例的接口，表明 Man 可以借 car 给别人
     */
    Car car();

}
