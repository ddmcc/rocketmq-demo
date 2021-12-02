package com.ddmcc.transaction.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddmcc.transaction.message.model.entity.Order;

/**
 * -Service
 *
 * @author jiangrz
 * @date 2021-12-01 09:50
 */
public interface IOrderService extends IService<Order> {

    /**
     * 创建订单
     *
     * @param goodsId  商品ID
     * @return java.lang.Boolean
     * @author jiangrz
     * @date 2021/12/1 9:57 上午
     */
    Boolean prepareOrder(Long goodsId);

    /**
     * 创建订单
     *
     * @param order  order
     * @param transactionId 事务ID
     * @return java.lang.Boolean
     * @author jiangrz
     * @date 2021/12/1 9:57 上午
     */
    Boolean createOrder(Order order, String transactionId);
}