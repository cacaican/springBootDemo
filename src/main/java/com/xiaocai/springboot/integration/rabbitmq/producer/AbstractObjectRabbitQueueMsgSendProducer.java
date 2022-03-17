package com.xiaocai.springboot.integration.rabbitmq.producer;

import com.xiaocai.springboot.integration.rabbitmq.producer.constants.MqBaseIPConsts;
import com.xiaocai.springboot.integration.rabbitmq.producer.constants.SingleFlagEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/17 15:57
 */
public abstract class AbstractObjectRabbitQueueMsgSendProducer {

    private static final Logger POLICY_GENERATE_LOGGER = LoggerFactory
            .getLogger(AbstractObjectRabbitQueueMsgSendProducer.class);

    /**
     * 消息发送
     *
     * @param entityOb
     *            业务单实体
     * @param directExchangeName
     *            exchangeName名称
     * @param routingKey
     *            路由KEY
     * @param policyGenerateSendCallBack
     *            回调实现类
     * @param params
     *            额外参数
     */
    public void sendMessage(String directExchangeName, String routingKey, Object entityOb, Map<String, Object> params,
                            IPolicyQueueMsgSendCallBack policyGenerateSendCallBack) {

       /* entityOb = policyGenerateSendCallBack.beforeSend(entityOb);
        Date sendTime = new Date();
        Map<String, Object> bodyDataMap = new HashMap<String, Object>();
        bodyDataMap.put("singleFlag", SingleFlagEnum.YES.name());
        bodyDataMap.put("businessEntityFlag", BusinessEntityFlag.NO.name());
        bodyDataMap.put("sendTime", sendTime);
        bodyDataMap.put("sendIp", MqBaseIPConsts.IPADDRESS);
        bodyDataMap.putAll(params);
        HeadlineMessageContent messageContent = new HeadlineMessageContent();
        messageContent.setSendTime(DateUtils.getCurrentTimestamp());
        bodyDataMap.put("entity", entityOb);
        messageContent.setBody(JsonUtils.toJsonString(bodyDataMap, true));
        Object generateId = batchImportIdGeneratorService.get();
        Message<MessageContent> message = new Message<MessageContent>();
        message.setMessageId((Long) generateId);
        message.setContent(messageContent);
        message.setStatus(Message.Status.Unreceived);
        this.doSendMessage(directExchangeName, routingKey, sendTime, message, policyGenerateSendCallBack, 0);*/
    }

}
