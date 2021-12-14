package com.ddmcc.transaction.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddmcc.transaction.message.model.dto.OrderDTO;
import com.ddmcc.transaction.message.model.entity.Cart;

/**
 * -Service
 *
 * @author jiangrz
 * @date 2021-12-01 09:50
 */
public interface ICartService extends IService<Cart> {


    /**
     * 删除购物车
     *
     * @param orderDTO
     * @author jiangrz
     * @date 2021/12/14 10:15 下午
     */
    void removeGoods(OrderDTO orderDTO);


}