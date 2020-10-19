package com.nan.day12_pattern_template;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    static ThreadPoolExecutor threadPoolExecutor;

    // Queue 的参数
    // BlockingQueue: 先进先出的一个队列 FIFO
    // SynchronousQueue: 线程安全的队列，它里面是没有固定的缓存的（OKHttp Dispatcher所使用的）
    // PriorityBlockingQueue: 无序的可以根据优先级进行排序 ，指定的对象要实现 Comparable 作比较(比如图片框架会用到,比如后面滑动出来的图片优先加载展示)
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(4);

    static {
        threadPoolExecutor = new ThreadPoolExecutor(
                4,// 核心线程数，就是线程池里面的核心线程数量
                10, // 最大线程数，线程池中的最大线程数
                60,// 线程存活的时间，没事干的时候的空闲存活时间，超过这个时间线程就会被销毁
                TimeUnit.SECONDS,// 线程存活时间的单位
                sPoolWorkQueue,// 线程队列
                new ThreadFactory() {// 线程创建工厂，如果线程池需要创建线程就会调用 newThread 来创建
                    @Override
                    public Thread newThread(@NonNull Runnable r) {
                        Thread thread = new Thread(r,"自己线程的名字");
                        thread.setDaemon(false); // 不是守护线程
                        return new Thread(r);
                    }
                });
    }

    public static void main(String[] args) {
        /*
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("下载图片显示完毕");
                }
            }).start();
        }
        */

        // 报错原因(AsyncTask也有):RejectedExecutionException会使得任务丢失
        // 线程队列4 最大线程数10 Runnable有20个放不进了就会抛异常
        for (int i = 0; i < 20; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("下载图片显示完毕"+Thread.currentThread().getName());
                }
            };
            // 加入线程队列，寻找合适的时机去执行
            threadPoolExecutor.execute(runnable);
        }
    }
}
