# day12

1. 模板设计模式
定义：
一个操作中的算法的框架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。
 
套路：
必须得有继承，父类一般都是流程和通用部分的封装，子类一般都是具体的功能和逻辑
 
2. 讲个实例
银行办事流程：领号、填表、办理业务（流程一样，但是每个人都不一样）
 
 
3. 源码中的模板设计模式（重点）
 
Activity的生命周期采用了什么设计模式？（模板）
首先都是继承自 Activity ，而且所有要自己定义的Activity（LoginActivity）的生命周期的流程都是一样的，每个Activity可以设置不同的界面，可以实现自己的具体交互逻辑
 
AsyncTask: 线程池
 
自定义View部分的：draw()  谁调用了 ViewGroup 调用了 View 的 draw() 方法，ViewGroup 也是 View，谁调用了 ViewGroup 的 draw() 方法？View
View的绘制流程   ViewRootImpl  , 
drawBackground(canvas);// 绘制背景
// Step 3, draw the content
if (!dirtyOpaque) onDraw(canvas); // 绘制自己
dispatchDraw(canvas);// 绘制子孩子
 
4. 开发中 BaseActivity  
钩子：模板设计模式用来控制父类的流程 ， 插件化开发hook启动流程(控制其他类的流程)
 
 
5. OkHttp 的 Dispatcher (线程池)
 
获取图片，图片联网下载，100 张，开线程下载 100 张图片就要是 100 个线程，线程开得太多会影响效率和吞吐量
 
线程执行时间：T=T1(创建的时间)+T2(run方法执行的时间)+T3(销毁的时间)
线程池好处：解决线程反复创建和销毁，可以做到复用
 
线程池理解：线程池、runnable队列、存活时间、线程的创建和销毁
