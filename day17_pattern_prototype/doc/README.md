# day17

1. 模式的定义
用原型实例指定创建对象的种类，并通过拷贝这些原型创建新的对象。

通过拷贝复制出一个新的对象。（拷贝）最简单的设计模式

2. 订单拆分
深圳，中山木村塑胶有限公司（日本）（流水线，搬运工）

IBox(箱子的接口)
PlasticClampBox(塑料夹子的箱子)
CarPartBox(汽车零件的箱子)
TurnkCar(卡车)

可以自己写
系统自带的拷贝：实现 Cloneable，复写 clone() 方法

SplitService 和 IBox 是什么关系？

3. 深拷贝和浅拷贝

浅拷贝（shallowCopy）只是增加了一个指针指向已存在的内存地址
深拷贝（deepCopy）是增加了一个指针并且申请了一个新的内存，使这个增加的指针指向这个新的内存

浅复制：仅仅是指向被复制的内存地址，如果原地址发生改变，那么浅复制出来的对象也会相应的改变
深复制：在计算机中开辟一块新的内存地址用于存放复制的对象,使用深拷贝的情况下，释放内存的时候不会因为出现浅拷贝时释放同一个内存的错误。

4. 模式的运用

4.1 Intent : 
4.2 ArrayList：
4.3 OkHttp：