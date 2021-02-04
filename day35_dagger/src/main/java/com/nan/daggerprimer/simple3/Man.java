package com.nan.daggerprimer.simple3;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

public class Man {

    @Inject
    Car car;

    /**
     * 有时我们想注入的依赖在使用时再完成初始化，加快加载速度，就可以使用注入Lazy<T>。只有在调用 Lazy 的 get() 方法时才会初始化依赖实例注入依赖
     */
    @Inject
    Lazy<Car> car1;

    /**
     * 有时候不仅仅是注入单个实例，我们需要多个实例，这时可以使用注入Provider<T>，每次调用它的 get() 方法都会调用到 @Inject 构造函数创建新实例或者 Module 的 provide 方法返回实例。
     */
    @Inject
    Provider<Car> car2;

    public Man() {
        DaggerManComponent.create().injectMan(this);
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Man man = new Man();
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                man.car.run();
                man.car1.get().run();

                man.car2.get().run();
                man.car2.get().run();
                man.car2.get().run();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}