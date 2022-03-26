package com.xiaocai.springboot.integration.threadPool.controller;

import com.xiaocai.springboot.integration.threadPool.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/25 15:44
 */
@RestController
@RequestMapping("/threadPool")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/testTaskService")
    public void testTaskService() throws InterruptedException {
        for (int i=0 ;i<100;i++){
            taskService.execute();
        }
        System.out.println("当前毫秒值----"+System.currentTimeMillis());

    }

    @RequestMapping("/testTaskService2")
    public void testTaskService2() throws InterruptedException {
        for (int i=0 ;i<100;i++){
            taskService.execute2();
        }
        System.out.println("当前毫秒值----"+System.currentTimeMillis());

    }
}
