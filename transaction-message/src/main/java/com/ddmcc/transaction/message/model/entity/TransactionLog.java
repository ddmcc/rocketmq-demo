package com.ddmcc.transaction.message.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * -Entity
 *
 * @author jiangrz
 * @date 2021-12-01 09:50
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("t_transaction_log")
public class TransactionLog {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String businessId;

    /**
     * 
     */
    private String transactionId;

}