package com.ddmcc.transaction.message.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

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
@TableName("t_cart")
public class Cart {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private Long goodsId;

    /**
     * 
     */
    private Date createAr;

    private Integer status;

    private String orderId;

}