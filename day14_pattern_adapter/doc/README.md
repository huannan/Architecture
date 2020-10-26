# day14

1. 重讲一下属性动画源码
1.   static String getMethodName(String prefix, String propertyName) {// prefix = set , propertyName = scaleX
2.        if (propertyName == null || propertyName.length() == 0) {
3.            // shouldn't get here
4.            return prefix;
5.        }
6.        // propertyName 第一个字母变成大写  S
7.        char firstLetter = Character.toUpperCase(propertyName.charAt(0));
8.        // 截取 propertyName 第一个字母到最后  acleX
9.        String theRest = propertyName.substring(1);
10.        // 返回方法名 = set + S + caleX = setScaleX
11.        return prefix + firstLetter + theRest;
12.    }

mSetter.invoke(target, mTmpValueArray);
属性动画说白了就是，android的jni层每隔 16ms 不断的回调 ObjectAnimator 的 doAnimationFrame()方法，
最终其实就是通过反射方法去掉用 View.setXxx(float); 比如 view.SetScaleX(1.0f),view.setTranslationY(0.5f)

2. 模式的例子 - 支付事例
比如支付系统，第一个版本只是支持 RMB , 第二个版本需要支持 USD


3. 模式定义 : 
适配器模式就是将某一些对象转成我们需要的适配的对象。分为两种一种是
类适配：请看 simple2

对象适配（常见）：请看 simple3

RecyclerView 从后台拿来的是 列表数据（对象数组），RecyclerView 里面显示的是 View , 基于两者不匹配，所以就采用 Adapter 设计模式，讲对象数组适配成需要的 View

插座：插座上面都是两个空的


4. Adapter开发中运用

主要还是这个 自定义View用得比较多，BannerView 轮播条，IndicatorView ViewPager的指示器，FlowLayout 热门标签等等 


自己写一个简单的 ListView 