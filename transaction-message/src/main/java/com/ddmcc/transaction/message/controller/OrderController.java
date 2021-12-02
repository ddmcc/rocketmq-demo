package com.ddmcc.transaction.message.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.ddmcc.transaction.message.service.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * File Description
 *
 * @author jiangrz
 * @date 2021-12-01 10:31
 */
@RequestMapping("/order")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final IOrderService orderService;

    @GetMapping("/create")
    public R<Boolean> createOrder(Long goodsId) {
        return R.ok(orderService.prepareOrder(goodsId));
    }

}
