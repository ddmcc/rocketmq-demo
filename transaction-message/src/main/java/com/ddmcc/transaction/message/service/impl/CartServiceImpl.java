package com.ddmcc.transaction.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddmcc.transaction.message.model.entity.Cart;
import com.ddmcc.transaction.message.mapper.CartMapper;
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



}