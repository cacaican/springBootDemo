package com.xiaocai.springboot.integration.ssm.controller;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/24 16:58
 */
public interface TaskController2 {


    Boolean executeTask(String taskName, String methodName);

    Boolean executeTaskWithCallBack(String taskName, String methodName);
}
