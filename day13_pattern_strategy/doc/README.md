# day13

1. 模式定义
策略模式定义了一系列算法，并将每一个算法封装起来，而且是他们之间可以相互切换。

写过几次：网络切换，OKhttp , xUtils , Retrofit , (六大基本原则中的开闭原则，里氏替换原则)

2. 策略模式 - 案例 - 投资理财
                      计算 10000 元的利息和本金
                      支付宝：
                                   3 个月  4.7% 
                                   6 个月  5.0%
                                   12个月 6.0%

                     人众金服：
                                  3 个月  9%
                                  6 个月  11.2%
                                  12 个月 12%
                               

开发中的使用场景 ? 
第三方的：
Glide 的缓存策略，ImageLoader 的生成图片文件的命名策略，第一次课讲的网络的切换和作业缓存，timber 日志打印工具的策略，OKhttp 部分
源码里面：
属性动画源码分析，setInterpolator差值器
RecyclerView: setLayoutManager 可以是 ListView 样式或者是 GirdView 样式等等 

3. 属性动画源码分析 （作业：UML画一个时序图）
怎么样去学习？属性动画是怎么实现的？

2年概念标准？无论什么源码，你能点进去就看，能够建立起学习的态度，学习能力能提升上来

自己先看看源码，看不懂怎么办？

ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"translationY",1f);
总结：new 了一个 ObjectAnimator 对象 ，设置了两个变量 mTarget 和  mPropertyName , 
然后调用 setFloatValues 方法 把后面所传的 float 参数封装成了 PropertyValuesHolder，
ValuesHolder 里面 是一个 FloatKeyFrame 数组 
setInterpolator 和 setDuration 只是给ObjectAnimator 属性设置了值 

看源码的时候有时看不懂怎么办？网上跟着别人的一起看，在一定情况下会有冲突，
你的源码和网上的源码可能不太一样，大致的流程肯定都是一样，以自己目前的源码为主

animateValue() 是一个核心方法

4. Log日志输出策略

https://github.com/JakeWharton/timber

怎么使用？