package com.ddmcc.transaction.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddmcc.transaction.message.mapper.TransactionLogMapper;
import com.ddmcc.transaction.message.model.entity.TransactionLog;
import com.ddmcc.transaction.message.service.ITransactionLogService;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * File Description
 *
 * @author jiangrz
 * @date 2021-12-02 22:22
 */
@Slf4j
@Service
public class TransactionLogServiceImpl extends ServiceImpl<TransactionLogMapper, TransactionLog>
    implements ITransactionLogService {


}
