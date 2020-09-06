# day09_pattern_builder

1.Builer 设计模式介绍
又称建造者模式，将构建过程和表示过程进行分离，让（参数）构建过程变得更加的简单和直观。

2.Builer 设计模式实例
OKhttp : 
1.        // connectTimeout().addNetworkInterceptor().addInterceptor() 其实是要给OKHttp用的
2.        // 按照一般的套路基本都是 OkHttpClient client = new OkHttpClient();
3.        //                        client.setTimeOut();
4.        //                        client.setNetworkInterceptor();
5.        //                        client.setInterceptor();
6.        /*OkHttpClient client = new OkHttpClient.Builder().connectTimeout()
7.                .addNetworkInterceptor().addInterceptor().build();*/
Dialog : 
使用场景什么时候该用：对象构建过程相对来说比较复杂的情况下（参数比较多的情况）

3.Dialog 源码分析

AlertDialog : 最终构建的对象（Dialog）
Builder ：用于构建我们的 Dialog
AlertParams：存放参数

说白了多看几次，多用几次，自己多写几次，就可以了

4. Builder设计模式和链式调用的区别
这个完全是两个概念，Builer设计模式是一种设计模式，链式调用只是一种调用方式，
但是一般来讲 Builder 设计模式一般会采用链调用的这种方式，那么并不是所有的链式调用都是 Builer 设计模式

链式调用有一个体现，就是在调用方法的时候返回自身对象，Builder 一般也有一种体现，就是一般都会出现 Builder 对象。

3.构建增强版的 NavigationBar 

我们打开一个 app 头部都是怎么构建的？




如果今天的内容有点听不懂，请先去看看下面的内容

http://www.jianshu.com/p/570d8bddb5c0
http://www.jianshu.com/p/87288925ee1f