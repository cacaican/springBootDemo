package com.xiaocai.springboot.integration.event.evnet;

import com.xiaocai.springboot.integration.event.service.AbstractEventHandler;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @description:
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
