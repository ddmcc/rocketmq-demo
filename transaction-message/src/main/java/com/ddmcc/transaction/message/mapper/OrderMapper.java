package com.ddmcc.transaction.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddmcc.transaction.message.model.entity.Order;

import org.apache.ibatis.annotations.Mapper;

/**
 * -Mapper
 *
 * @author jiangrz
 * @date 2021-12-01 09:50
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}