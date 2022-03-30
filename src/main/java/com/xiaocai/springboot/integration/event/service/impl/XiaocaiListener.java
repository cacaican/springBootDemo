package com.xiaocai.springboot.integration.event.service.impl;

import com.xiaocai.springboot.integration.event.evnet.AbstractEvent;
import com.xiaocai.springboot.integration.event.service.AbstractEventHandler;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @description: 事务监听执行, 两种实现方式，一个是类继承ApplicationListener，一个是使用@EventListener注解（这里使用TransactionalEventListener，对调用事务做了一层判断）
 * @author: xiaocai
 * @time: 2022/3/30 10:47
 */
@Component
public class XiaocaiListener  /*implements ApplicationListener*/ {

    /*
    TransactionalEventListener注解的作用 :表示事件发布代码所在事务操作成功/失败后执行
    BEFORE_COMMIT,指定目标方法在事务commit之前执行
    AFTER_COMMIT,指定目标方法在事务commit之后执行
    AFTER_ROLLBACK, 指定目标方法在事务rollback之后执行
    AFTER_COMPLETION 指定目标方法在事务完成时执行，这里的完成是指无论事务是成功提交还是事务回滚了
    ————————————————
    版权声明：本文为CSDN博主「Evan Wang」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/qq_41378597/article/details/105748703*/

//  @EventListener(value = AbstractEvent.class)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, value = AbstractEvent.class)
    public void onAbstractEvent(AbstractEvent event) {
        event.getHandle().execute(event);
    }
}
