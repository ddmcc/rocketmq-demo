package com.ddmcc.transaction.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddmcc.transaction.message.model.entity.TransactionLog;

import org.apache.ibatis.annotations.Mapper;

/**
 * File Description
 *
 * @author jiangrz
 * @date 2021-12-02 22:19
 */
@Mapper
public interface TransactionLogMapper extends BaseMapper<TransactionLog> {
}
