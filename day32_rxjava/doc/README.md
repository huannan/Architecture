# day32

### 学习

圣人无常师，无常师者乃大师也。
知识付费课程怎么选择和学习：
1. 充分了解，多看几次人家的东西，看自己能否坚持(*)
2. 课程成不成体系，基础 -> 进阶
3. 鸡血要打，千万别天天打
架构课程学完之后能够达到的标准: 主流框架要自己能分析源码（Glide图片，Json解析，Dagger注入）

成功 = 方向（正确）+ 坚持 + 努力 + 时间

### RxJava

RxJava入门文章
http://www.jianshu.com/p/464fa025229e 

RxJava（2.0） 思想：响应式编程（异步事件流编程）流
流: 类似于河流,一条线,有起点有终点,可以拦截/过滤
每一个只管上游(source)跟下游(包裹一层observer)

正常的观察者模式:一对多,被观察者变化的时候通知观察者
RxJava的观察者模式:一对一,一旦被观察者被注册监听,就通知观察者发生改变

### 事件变换与执行流程

核心：每一个只管上游(source)跟下游(包裹一层observer)

### 线程切换(线程池 + handler)

所有的第三方框架共性：都是封装（本质是不会变得），butterknife -> findViewById , OkHttp -> Socket + okio 

```java
子线程切换

1.   final class SubscribeTask implements Runnable {
2.        private final SubscribeOnObserver<T> parent;
3.
4.        SubscribeTask(SubscribeOnObserver<T> parent) {
5.            this.parent = parent;
6.        }
7.
8.        @Override
9.        public void run() {
10.            source.subscribe(parent);
11.        }
12.    }

scheduler.scheduleDirect(Runnable),scheduler.io() 就是 -> 
IO = RxJavaPlugins.initIoScheduler(new IOTask()); -> DEFAULT = new IoScheduler(); -> 创建一个线程池的封装对象

1.  public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
2.        // createWorker() -> IOScheduler 的 createWorker() -> EventLoopWorker
3.        final Worker w = createWorker();
4.
5.        final Runnable decoratedRun = RxJavaPlugins.onSchedule(run);
6.
7.        DisposeTask task = new DisposeTask(decoratedRun, w);
8.        // w.schedule -> EventLoopWorker的 schedule 
9.
10.        w.schedule(task, delay, unit);
11.
12.        return task;
13.    }

解析 主线程切换：

1. // MainThreadScheduler 策略
2. Scheduler.Worker w = scheduler.createWorker();
3. // 调用上游的 subscribe，对下游的 observer 进行代理包裹 ObserveOnObserver
4.
5. source.subscribe(new ObserveOnObserver<T>(observer, w, delayError, bufferSize));


对下游的处理
worker.schedule(this); 

new Handler(Looper.getMainLooper()) 精华 new Handler() 和 new Handler(Looper.getMainLooper()) 的区别:
1. new Handler() , 如果是在主线程中是没问题，但是有的时候可能会在子线程中调用，肯定就报错
2. new Handler(Looper.getMainLooper())，确保创建的Handler永远在主线程中，Looper要是主线程的Looper

1.            ScheduledRunnable scheduled = new ScheduledRunnable(handler, run);
2.
3.            Message message = Message.obtain(handler, scheduled);
4.            message.obj = this; // Used as token for batch disposal of this worker's runnables.
5.            // 只不过把消息发出去，但是消息并有执行
6.
7.            handler.sendMessageDelayed(message, Math.max(0L, unit.toMillis(delay)));

```

### 线程安全

CopyOnWriteArrayList

写的时候,直接拷贝一份新的数据,保证并发读安全:

```java
public boolean add(E e) {
    synchronized (lock) {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    }
}
```
