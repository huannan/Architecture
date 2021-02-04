package com.nan.daggerprimer.simple2;

import dagger.Module;
import dagger.Provides;

/**
 * Module 是提供依赖的对象实例的另一种方式
 * 可以用@Provides标注的方法来提供依赖实例，方法的返回值就是依赖的对象实例，@Provides方法必须在Module中，Module 即用@Module标注的类
 * Module的优先级比@Inject标注构造函数的高，意味着 Dagger 2 会先从 Module 寻找依赖实例
 */
@Module
public class CarModule {

    @Provides
    public Car provideCar() {
        return new Car();
    }

}
