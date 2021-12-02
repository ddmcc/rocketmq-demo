package com.ddmcc.transaction.message.mq;

import com.ddmcc.transaction.message.mq.listener.OrderListener;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;

/**
 * File Description
 *
 * @author jiangrz
 * @date 2021-12-02 22:47
 */
@Component
@RequiredArgsConstructor(onConstructor = @__({@Lazy, @Autowired}))
public class OrderTransactionProducer {


    private final OrderListener orderListener;
    private final RocketMQProperties properties;

    private TransactionMQProducer producer;

    @PostConstruct
    public void init(){
        producer = new TransactionMQProducer("order");
        producer.setNamesrvAddr(properties.getNameServer());
        producer.setTransactionListener(orderListener);
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public TransactionSendResult send(String data, String topic) throws MQClientException {
        Message message = new Message(topic, data.getBytes());
        return this.producer.sendMessageInTransaction(message, null);
    }
}
