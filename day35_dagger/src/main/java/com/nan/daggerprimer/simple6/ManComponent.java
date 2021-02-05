package com.nan.daggerprimer.simple6;

import dagger.BindsInstance;
import dagger.Component;

/**
 * 通过前面作用域的讲解，可以清楚 Component 可以间接持有 Module 或 Inject 目标类构造函数提供的依赖实例
 * 除了这两种方式，Component 还可以在创建 Component 的时候绑定依赖实例，用以注入
 * 优先使用@BindsInstance方法，相对于写一个带有构造函数带有参数的 Module
 */
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
