# day04_java_basics

1. 反射
作用：能够做一般做不到的事情。使用场景：插件式换肤 ，插件式开发  apk(没有安装插件) 
所有的反射功能都是基于我们字节码（class），一个类的 class 在内存中应该只有一份，而且 class 其实也是一个对象 Class

1.1  获取构造函数
class.getDeclaredConstructor()
1.2  获取方法
class.getDeclaredMethod()
1.3  获取属性
class.getDeclaredFiled()

2. 注解

注解只是一个标识（标记），没有具体的功能逻辑代码。
butterknife 用了反射没有？ 用了 1 （new 对象的时候），没有 2  Class.fromName().newInstance();

通过反射和注解去 findViewById 


3. 泛型

类泛型 在任何地方出现的，代表的是统一类型 

方法泛型

泛型的上限

泛型的下限