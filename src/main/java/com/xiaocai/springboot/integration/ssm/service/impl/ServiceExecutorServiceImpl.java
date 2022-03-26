package com.xiaocai.springboot.integration.ssm.service.impl;

import com.xiaocai.springboot.integration.ssm.config.BaseServiceLocator;
import com.xiaocai.springboot.integration.ssm.service.IServiceExecutorService;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.lang.reflect.InvocationTargetException;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/24 17:12
 */
public class ServiceExecutorServiceImpl implements IServiceExecutorService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    /*@Autowired
    private IMessageSender messageSender;*/

    public ServiceExecutorServiceImpl() {
    }

    @Override
    @Async
    public void execute(String serviceName, String methodName, Object[] paramArr) {
        Object service = BaseServiceLocator.getService( serviceName);
        if (service != null) {
            try {
                MethodUtils.invokeMethod(service, methodName, paramArr);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            this.logger.error("服务名称:" + serviceName + " 不存在!");
        }
    }

    @Async
    @Override
    public ListenableFuture<Object> executeFuture(String serviceName, String methodName, Object[] paramArr) {
        Object service = BaseServiceLocator.getService(serviceName);
        if (service != null) {
            Object r = null;
            try {
                r = MethodUtils.invokeMethod(service, methodName, paramArr);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return new AsyncResult(r);

        } else {
            this.logger.error("服务名称:" + serviceName + " 不存在!");
            return null;
        }    }
}
