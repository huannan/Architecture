# day19

解答如何编译 .so 库

准备的东西：AS 2.2 版本以上  CMake  
                     安装 ndk 开发的环境，下载 CMake 的支持，调试支持   （3个东西）
准备的知识：C基础 ，C++基础 ，Linux Shell 操作基础 （Mac Nuix）

下载的都是一些 C 和 C++ 的一些源码，我们要编译成 .so 
（编译 .so 很重要）
windows 下面也有一些办法，但是有局限性
依靠两个文件  Android.mk   Application.mk 怎么编译

打一个比方 7zip 压缩 ，可以去网上下载别人写好的 Android.mk  Application.mk ，执行 ndk-build 命令就行


1. 责任链设计模式
    1.1 模式定义
      使多个对象都有机会处理请求，将这些对象连成一条链，并沿着这条链处理该请求，只道有对象处理他为止。

    1.2 模式运用
    系统哪里用了，一个非常常见的地方，View机制的事件的分发
