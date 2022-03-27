package com.xiaocai.springboot.javase.thread.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/26 22:42
 */
public class MyThreadPool {
    public static void main(String[] args) {
        testMyThreadPool();
    }

    private static void testMyThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("我自己定义的线程池");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        threadPoolTaskExecutor.setCorePoolSize(2);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(10);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        threadPoolTaskExecutor.setAwaitTerminationMillis(5000);

        threadPoolTaskExecutor.initialize();
        threadPoolTaskExecutor.execute(() ->{
            for (int i = 0; i < 10; i++) {
                System.out.println("我自己定义的："+Thread.currentThread().getName()+"任务打印--"+ i);

            }
        });
    }
}
