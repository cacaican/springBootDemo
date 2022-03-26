package com.xiaocai.springboot.integration.threadPool.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/25 15:32
 */
@Configuration
public class SpringThreadPoolConfig {

    @Bean(value = "springDefaultThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4);//设置初始值
        taskExecutor.setMaxPoolSize(10);//设置最大值

        taskExecutor.setQueueCapacity(50);
        taskExecutor.setKeepAliveSeconds(60);

        taskExecutor.setThreadNamePrefix("自定义线程池-");
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        return taskExecutor;
    }

    @Bean(value = "simpleAsyncTaskExecutor")
    public SimpleAsyncTaskExecutor getThreadPoolTaskExecutor2(){
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setThreadGroupName("Simple组");//设置线程组名称
        taskExecutor.setTaskDecorator(new TaskDecorator() {//主要用例是围绕任务的调用设置一些执行上下文，或者为任务执行提供一些监控/统计。
            @Override
            public Runnable decorate(Runnable runnable) {
                return null;
            }
        });
        taskExecutor.setThreadNamePrefix("自定义SimpleAsync---");
        taskExecutor.setThreadPriority(0);
        taskExecutor.setConcurrencyLimit(10); //默认是-1没有限制
        return taskExecutor;
    }

    @Bean
    @Primary
    public ThreadPoolTaskExecutor defaultExecutor() {
        ThreadPoolTaskExecutor defaultExecutor = new ThreadPoolTaskExecutor();
        if (StringUtils.isNotBlank("10-20")) {
            String min = StringUtils.substringBefore("10-20", "-");
            String max = StringUtils.substringAfter("10-20", "-");
            if (StringUtils.isNotBlank(min)) {
                defaultExecutor.setCorePoolSize(Integer.valueOf(min.trim()));
            }

            if (StringUtils.isNotBlank(max)) {
                defaultExecutor.setMaxPoolSize(Integer.valueOf(max.trim()));
            }
        }

        defaultExecutor.setQueueCapacity(100);
        defaultExecutor.setThreadNamePrefix("我默认的primary线程池");
        return defaultExecutor;
    }
}
