package com.xiaocai.springboot.integration.rabbitmq.producer;

/**
 * @description: 保单发送后的回调方法
 * @author: xiaocai
 * @time: 2022/3/17 15:59
 */
public interface IPolicyQueueMsgSendCallBack {

    /**
     * 发送消息前处理
     *
     * @param entityObject
     *            业务单子实体
     * @return object
     */
    Object beforeSend(Object entityObject);

    /**
     * 发送成功后回调
     * @param response  结果返回
     */

    void onCompletedSending(Object response);

    /**
     * 失败后结果回调
     * @param response  结果返回
     */
    void onErrorSending(Object response);
}
