package com.ddmcc.transaction.message.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddmcc.transaction.message.mapper.CartMapper;
import com.ddmcc.transaction.message.model.dto.OrderDTO;
import com.ddmcc.transaction.message.model.entity.Cart;
import com.ddmcc.transaction.message.service.ICartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {


    @Override
    public void removeGoods(OrderDTO orderDTO) {
        if (this.count(Wrappers.<Cart>lambdaQuery().eq(Cart::getOrderId, orderDTO.getId())) > 0) {
            log.warn("订单 {} 已消费", orderDTO.getId());
        } else {
            Cart cart = this.getOne(Wrappers.<Cart>lambdaQuery().eq(Cart::getGoodsId, orderDTO.getGoodsId()));
            if (cart != null) {
                cart.setOrderId(orderDTO.getId());
                cart.setStatus(1);
                this.updateById(cart);
                log.info("删除购物车：{}", orderDTO.getGoodsId());
            }
        }
    }
}