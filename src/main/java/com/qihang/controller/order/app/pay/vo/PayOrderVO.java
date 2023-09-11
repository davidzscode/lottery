package com.qihang.controller.order.app.pay.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qihang
 * @since 2022-10-13
 */
@Data
public class PayOrderVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "订单类型 0 充值")
    private String type;

    @ApiModelProperty(value = "金额")
    private BigDecimal price;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
