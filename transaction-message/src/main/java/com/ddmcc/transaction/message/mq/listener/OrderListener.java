package com.ddmcc.transaction.message.mq.listener;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddmcc.transaction.message.model.dto.OrderDTO;
import com.ddmcc.transaction.message.model.entity.Order;
import com.ddmcc.transaction.message.model.entity.TransactionLog;
import com.ddmcc.transaction.message.service.IOrderService;
import com.ddmcc.transaction.message.service.ITransactionLogService;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * File Description
 *
 * @author jiangrz
 * @date 2021-12-01 11:30
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderListener implements TransactionListener {

    private final IOrderService orderService;
    private final ITransactionLogService transactionLogService;

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.info("开始执行本地事务.....");

        OrderDTO orderDTO = JSONObject.parseObject(message.getBody(), OrderDTO.class);

        Order order = new Order();
        order.setGoodsId(orderDTO.getGoodsId());
        order.setId(orderDTO.getId());
        order.setCreateAt(new Date());

        String transactionId = message.getTransactionId();
        orderService.createOrder(order, transactionId);
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        log.info("开始执行本地事务回查.....");
        int count = transactionLogService.count(Wrappers.<TransactionLog>lambdaQuery()
            .eq(TransactionLog::getTransactionId, messageExt.getTransactionId())
        );
        log.info("本地事务回查结果：{}", count);
        if (count > 0) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }
        return LocalTransactionState.UNKNOW;
    }
}
