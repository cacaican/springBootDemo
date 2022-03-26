package com.xiaocai.springboot.integration.threadPool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/25 15:41
 */
@Service
public class TaskService  {


    //如果不指定，使用默认的SimpleAsyncTaskExecutor
    @Async(/*value = "springDefaultThreadPoolTaskExecutor"*/)
    public void execute() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("TaskService 对象执行输出任务:" + Thread.currentThread().getName()+"----"+System.currentTimeMillis());
    }

    @Autowired
    private ThreadPoolTaskExecutor springDefaultThreadPoolTaskExecutor;

    public void execute2() throws InterruptedException {
        /*springDefaultThreadPoolTaskExecutor.submit(()->{
            try {
                Thread.sleep(500);
                System.out.println("TaskService2 对象执行输出任务:" + Thread.currentThread().getName()+"----"+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/


        ListenableFuture<?> listenableFuture = springDefaultThreadPoolTaskExecutor.submitListenable(() -> {
            try {
                Thread.sleep(500);
                System.out.println("TaskService2 对象执行输出任务:" + Thread.currentThread().getName() + "----" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        listenableFuture.addCallback( new MyListenableFutureCallback());

//        springDefaultThreadPoolTaskExecutor.execute();
    }

}
class MyListenableFutureCallback  implements ListenableFutureCallback<Object> {

    /**
     * 批处理内容
     */
//    private List<Map<String, String>> lines;
    /**
     * 批处理状态对象
     */
//    private Object resultsBatchEO;
    /**
     * 当前等处理的线数量
     */
//    private AtomicInteger threadNum;


    @Override
    public void onFailure(Throwable ex) {
        System.out.println("失败了");

    }

    @Override
    public void onSuccess(Object result) {
        System.out.println("成功拉");
    }
}