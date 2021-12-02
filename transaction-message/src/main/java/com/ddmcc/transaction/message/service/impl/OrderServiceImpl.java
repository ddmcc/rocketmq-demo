package com.ddmcc.transaction.message.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddmcc.transaction.message.mapper.OrderMapper;
import com.ddmcc.transaction.message.model.dto.OrderDTO;
import com.ddmcc.transaction.message.model.entity.Order;
import com.ddmcc.transaction.message.model.entity.TransactionLog;
import com.ddmcc.transaction.message.mq.OrderTransactionProducer;
import com.ddmcc.transaction.message.service.IOrderService;
import com.ddmcc.transaction.message.service.ITransactionLogService;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * -Service-Impl
 *
 * @author jiangrz
 * @date 2021-12-01 09:50
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Lazy, @Autowired}))
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private final ITransactionLogService transactionLogService;
    private final OrderTransactionProducer producer;

    @Override
    public Boolean prepareOrder(Long goodsId) {
        OrderDTO order = new OrderDTO();
        order.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setGoodsId(goodsId);

        try {
            TransactionSendResult transaction = producer.send(JSONObject.toJSONString(order), "TRANSACTION");
            log.info("发送事务消息：{}", JSONObject.toJSONString(transaction));
        } catch (MQClientException e) {
            log.error("创建订单失败", e);
            throw new RuntimeException("创建订单失败");
        }
        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean createOrder(Order order, String transactionId) {
        log.info("创建订单：{}, transactionId： {}", JSONObject.toJSONString(order), transactionId);
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setTransactionId(transactionId);
        transactionLog.setBusinessId(order.getId());
        return this.saveOrUpdate(order) && transactionLogService.save(transactionLog);
    }


}