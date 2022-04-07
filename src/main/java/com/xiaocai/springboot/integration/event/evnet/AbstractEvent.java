package com.xiaocai.springboot.integration.event.evnet;

import com.xiaocai.springboot.integration.event.service.AbstractEventHandler;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @description:
 * spring中的事件
 * 上下文更新事件（ContextRefreshedEvent）：该事件会在ApplicationContext被初始化或者更新时发布。也可以在调用ConfigurableApplicationContext 接口中的refresh()方法时被触发。
 * 上下文开始事件（ContextStartedEvent）：当容器调用ConfigurableApplicationContext的Start()方法开始/重新开始容器时触发该事件。
 * 上下文停止事件（ContextStoppedEvent）：当容器调用ConfigurableApplicationContext的Stop()方法停止容器时触发该事件。
 * 上下文关闭事件（ContextClosedEvent）：当ApplicationContext被关闭时触发该事件。容器被关闭时，其管理的所有单例Bean都被销毁。
 * 请求处理事件（RequestHandledEvent）：在Web应用中，当一个http请求（request）结束触发该事件。
 *
 *
 * @author: xiaocai
 * @time: 2022/3/30 10:49
 */
public class AbstractEvent extends ApplicationEvent {

    /**ApplicationEvent中的构造器
     * @param source
     */
    public AbstractEvent(Object source) {
        super(source);
    }



    /**ApplicationEvent中的构造器
     * @param source
     * @param clock
     */
    public AbstractEvent(Object source, Clock clock) {
        super(source, clock);
    }

    /**
     * 设置当前event对应的处理handler
     * */
    private AbstractEventHandler eventHandler;

    /**
     * @param eventHandler policyHandle
     * @param source       source
     */
    public AbstractEvent(AbstractEventHandler eventHandler, Object source) {
        super(source);
        this.eventHandler = eventHandler;
    }

    public AbstractEventHandler getHandle() {
        return eventHandler;
    }
}
