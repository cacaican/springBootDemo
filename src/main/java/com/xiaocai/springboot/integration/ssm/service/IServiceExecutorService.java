package com.xiaocai.springboot.integration.ssm.service;

import org.springframework.util.concurrent.ListenableFuture;

public interface IServiceExecutorService {


    void execute(String serviceName, String methodName, Object[] paramArr);


    ListenableFuture<Object> executeFuture(String serviceName, String methodName, Object[] paramArr);


}
