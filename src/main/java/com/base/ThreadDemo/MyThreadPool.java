package com.xiaocai.base.ThreadDemo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MyThreadPool
 * @Description 自定义一个线程池
 * https://www.cnblogs.com/xgp123/p/12348733.html
 * https://blog.csdn.net/devzyh/article/details/121724517
 * @Author xiaocai
 * @Date 2022/3/1
 */
public class MyThreadPool {
    private ThreadPoolExecutor  ThreadPoolExecutor;

    public static void main(String[] args) {
        ThreadPoolExecutor executor;
        executor =  new ThreadPoolExecutor(
                1, // 最大核心线程数量
                2, // 最大线程数量 = 核心线程数量 + 临时线程数量
                1000, // 临时线程的最大空闲时间，超过这个时间将会被销毁
                TimeUnit.MILLISECONDS, // 时间单位
                new ArrayBlockingQueue<Runnable>(1), // 任务队列的容器
                new MyThreadFactory(), // 线程创建工厂类
                new MyRejectedExecutionHandler() // 任务拒绝处理策略
        );

        executor.submit(new MyRunner("core")); // 此时会创建一个核心线程执行任务
        executor.submit(new MyRunner("queue")); // 此时核心线程数达到最大，当前任务会被提交到任务队列等待
        executor.submit(new MyRunner("temp")); // 此时任务队列已满，将创建临时线程执行任务
        executor.submit(new MyRunner("reject")); // 此时超出线程执行任务能力，执行任务拒绝策略

        executor.shutdown(); // 关闭线程池


        Thread thread = new Thread(new MyRunner("测试"));
        thread.start();

    }

    static class  MyThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public MyThreadFactory() {
            this.namePrefix = "TestPool-" +
                    poolNumber.getAndIncrement() +
                    "-TestThread-";
        }



        @Override
        public Thread newThread(Runnable r) {
            // 指定自定义的线程名称
            return new Thread(r, this.namePrefix + threadNumber.getAndIncrement());
        }
    }

    /**
     * 线程任务
     */
    static class MyRunner implements Runnable {
        private String taskName;

        public MyRunner(String name) {
            this.taskName = name;
        }

        @Override
        public void run() {

            System.out.println("开始执行任务[" + this.taskName + "]，当前线程名称：" +
                    Thread.currentThread().getName());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务执行完毕[" + this.taskName + "]");
        }

        public String getTaskName() {
            return this.taskName;
        }
    }

    /**
     * 实现自己的任务拒绝处理策略
     * 超过线程池处理能力的任务会执行此策略
     * Java内置四种拒绝策略：
     * AbortPolicy: 抛出一个AbortExecutionException的异常
     * DiscardPolicy: 丢弃新提交的任务，没有任何通知
     * DiscardOldestPolicy: 丢弃任务队列头部的任务，没有任何通知
     * CallRunsPolicy: 将任务丢给提交任务的线程执行，即可以执行任务又可以延缓新任务的提交
     */
    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("线程池已满，1秒后重试任务");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.submit(r); // 重试
            System.out.println("任务以再次提交到线程池");
        }
    }

}


