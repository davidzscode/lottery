package com.qihang.controller.order.admin.pay.vo;

import com.qihang.common.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-12 15:38
 */
@Data
public class PayOrderQueryVO extends PageDTO {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "订单类型")
    private String type;

    @ApiModelProperty(value = "支付方式 0 支付宝 1.微信")
    private String payType;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "0 待支付 1 已支付")
    private String state;

    @ApiModelProperty(value = "金额")
    private BigDecimal price;

    @ApiModelProperty(value = "更新时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
