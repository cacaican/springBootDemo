package com.xiaocai.springboot.javase.thread.service;

import java.util.concurrent.*;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/26 22:17
 */
public class ThreadService {

    public static void main(String[] args) {
        testFixedThreadPool();
        testCachedThreadPool();
        testScheduledThreadPool();
        testSingleThreadExecutor();
    }

    private static void testSingleThreadExecutor() {
        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行；
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.submit(()-> {
            for (int i = 0; i < 10; i++) {
                System.out.println("单线程CachedThreadPool任务打印--"+ i);

            }
        });
    }

    private static void testScheduledThreadPool() {
        //创建一个定长线程池，支持定时及周期性任务执行。
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.schedule(()-> {
            for (int i = 0; i < 10; i++) {
                System.out.println("定时线程scheduledThreadPool任务打印--"+ i);

            }
        },100,TimeUnit.MILLISECONDS);

    }

    private static void testCachedThreadPool() {
        //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        newCachedThreadPool.submit(()-> {
            for (int i = 0; i < 10; i++) {
                System.out.println("固定线程CachedThreadPool任务打印--"+ i);

            }
        });

    }

    private static void testFixedThreadPool() {
        //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        fixedThreadPool.submit(()-> {
            for (int i = 0; i < 10; i++) {
                System.out.println("固定线程fixedThreadPool任务打印--"+ i);

            }
        });
    }


}
