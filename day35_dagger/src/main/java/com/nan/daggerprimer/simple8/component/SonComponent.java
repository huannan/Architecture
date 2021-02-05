package com.nan.daggerprimer.simple8.component;

import com.nan.daggerprimer.simple8.Son;
import com.nan.daggerprimer.simple8.module.BikeModule;
import com.nan.daggerprimer.simple8.scope.SonScope;

import dagger.Subcomponent;

/**
 * Component 的组织关系-继承
 * 继承关系和依赖关系最大的区别就是：继承关系中不用显式地提供依赖实例的接口，SubComponent 继承 parent Component 的所有依赖。
 */
@SonScope
@Subcomponent(modules = {BikeModule.class})
public interface SonComponent {

    void injectSon(Son son);

    /**
     * SubComponent 必须显式地声明 SubComponent.Builder，parent Component 需要用 Builder 来创建 SubComponent
     * SubComponent 表明它是继承扩展某 Component 的
     */
    @Subcomponent.Builder
    interface Builder {
        SonComponent build();
    }
}
