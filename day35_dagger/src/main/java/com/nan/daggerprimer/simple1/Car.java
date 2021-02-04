package com.nan.daggerprimer.simple1;

import javax.inject.Inject;

/**
 * 使用@Inject标注构造函数来提供依赖的对象实例的方法，不是万能的，在以下几种场景中无法使用：
 * 接口没有构造函数
 * 第三方库的类不能被标注
 * 构造函数中的参数必须配置
 */
public class Car {

    @Inject
    public Car() {
    }

    public void run() {
        System.out.println(this + " 汽车发动");
    }

}
