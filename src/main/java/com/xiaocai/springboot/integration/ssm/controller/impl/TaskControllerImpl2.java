package com.xiaocai.springboot.integration.ssm.controller.impl;

import com.xiaocai.springboot.integration.ssm.config.BaseServiceLocator;
import com.xiaocai.springboot.integration.ssm.controller.TaskController2;
import com.xiaocai.springboot.integration.ssm.service.IServiceExecutorService;
import com.xiaocai.springboot.integration.ssm.service.MyProcessCallBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/24 16:59
 */
public class TaskControllerImpl2 implements TaskController2 {

    /**
     *异步线程执行类
     */
    @Autowired
    private IServiceExecutorService executorService;

    @Override
    public Boolean executeTask(String taskName, String methodName) {

        executorService.execute(taskName, methodName,  new Object[]{null});
        return null;
    }

    @Override
    public Boolean executeTaskWithCallBack(String taskName, String methodName) {
        //回调集合
        List<ListenableFuture<Object>> listenableFutures = new ArrayList<>();
        //定义线程总数
        int threadNum = 10 ;
        ListenableFuture<Object> Future  = executorService.executeFuture(taskName, methodName, new Object[]{null});
        listenableFutures.add(Future);
        //lines = new ArrayList<LinkedMap<String, String>>(batchSize);
        Future.addCallback(new MyProcessCallBack(taskName, methodName, null, threadNum));

        return null;
    }
}
