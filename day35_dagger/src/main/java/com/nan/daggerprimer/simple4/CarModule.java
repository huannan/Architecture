package com.nan.daggerprimer.simple4;

import com.nan.daggerprimer.simple4.car.Car;
import com.nan.daggerprimer.simple4.car.Car1;
import com.nan.daggerprimer.simple4.car.Car2;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * 试想这样一种情况：沿用之前的 Man 和 Car 的例子，如果 CarModule 提供了两个生成 Car 实例的 provide 方法，Dagger 2 在注入 Car 实例到 Man 中时应该选择哪一个方法呢？
 * 这时 Dagger 2 不知道使用provideCar1还是provideCar2提供的实例，在编译时就会报错，这种情况也可以叫依赖迷失（网上看到的叫法）。而@Qualifier注解就是用来解决这个问题，使用注解来确定使用哪种 provide 方法。
 */
@Module
public class CarModule {

    @Provides
    @Named("car1")
    public Car provideCar1() {
        System.out.println("provideCar1 invoke");
        return new Car1();
    }

    @Named("car2")
    @Provides
    public Car provideCar2() {
        System.out.println("provideCar2 invoke");
        return new Car2();
    }

}
