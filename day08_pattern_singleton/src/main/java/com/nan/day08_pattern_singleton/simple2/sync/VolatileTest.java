package com.nan.day08_pattern_singleton.simple2.sync;

public class VolatileTest {

    private static boolean flag = true; //不用volatile修饰

    public static void main(String[] args) throws InterruptedException {
        new Thread1().start();
        Thread.sleep(1000); //暂停1秒保证线程1启动并运行。
        new Thread2().start();
    }

    /**
     * 线程1 一个循环，如果 flag为false 跳出循环
     */
    static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println("thread1-执行");
            while (flag) {

            }
            System.out.println("thread1-退出");
        }
    }

    /**
     * 线程2  2秒后将flag改成false
     */
    static class Thread2 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread2-执行");
            flag = false;
            System.out.println("flag被thread2改成false");
        }
    }
}
