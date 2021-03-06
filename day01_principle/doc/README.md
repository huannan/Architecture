# day01_principle

面向对象六大基本原则 - 网络引擎切换

https://www.jianshu.com/p/e1d9ddc86c7d

#### 1 单一职责原则
英文：Single Responsibility Principle
定义：就一个类而言，应该仅有一个引起它变化的原因。简单来说，一个类中应该是一组相关性很高的函数、数据的封装。

#### 2 开闭原则
英文：Open Close Principle
定义：软件中的对象（类、模块、函数等）应该对于扩展是开放的，但是，对于修改是封闭（关闭）的。当软件需要变化时，应该尽量通过扩展的方式来实现变化，而不是通过修改已有的代码来实现。

#### 3 里氏替换原则
英文：Liskov Substitution Principle
定义：只要父类能出现的地方子类就可以出现，主要体现就是实现和继承。

#### 4 依赖倒置原则
英文：Dependence Inversion Principle
定义：指代了一种特定的解耦形式，高层模块不依赖低层次模块的细节，说白了高层次就是不依赖细节而是依赖抽象。

#### 5 接口隔离原则
英文：Interface Segregation Principle
定义：类间的依赖关系应该建立在最小的接口上。接口隔离原则将非常庞大、臃肿的接口拆分成为更小的和更具体的接口，这样客户将会只需要知道他们感兴趣的方法。接口隔离原则的目的是系统解开耦合，从而容易重构、更改和重新部署，让客户端依赖的接口尽可能地小。（接口拆分，单接口）。

上面的这个五个原则  其实都更接口和抽象有关 （面向抽象和面向接口）。

#### 6 迪米特原则
英文：Law of Demeter
定义：一个对象应该对其他对象有最少的了解，调用者也是比较关注。