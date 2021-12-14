package com.ddmcc.transaction.message.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.ddmcc.transaction.message.model.dto.OrderDTO;
import com.ddmcc.transaction.message.service.ICartService;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * File Description
 *
 * @author jiangrz
 * @date 2021-12-14 21:16
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__({@Lazy, @Autowired}))
public class OrderConsumer{

    private final RocketMQProperties properties;
    private final ICartService cartService;


    @PostConstruct
    public void init() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order-consumer");
        consumer.setNamesrvAddr(properties.getNameServer());
        consumer.subscribe("TRANSACTION","*");
        consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
            log.info("消费者开始消费...");
            try{
                for (MessageExt message : list) {
                    log.info("开始处理订单数据，删除购物车....");
                    OrderDTO orderDTO = JSONObject.parseObject(message.getBody(), OrderDTO.class);
                    cartService.removeGoods(orderDTO);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e){
                log.error("删除购物车发生异常", e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        consumer.start();
    }

}
