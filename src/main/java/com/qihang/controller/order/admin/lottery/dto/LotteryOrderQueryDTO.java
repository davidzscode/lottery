package com.qihang.controller.order.admin.lottery.dto;

import com.qihang.common.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-12 15:38
 */
@Data
public class LotteryOrderQueryDTO extends PageDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "0 待支付 1 已支付")
    private String state;

    @ApiModelProperty(value = "0 足彩 1 篮彩 2 北京单场 3 排列3")
    private String type;

    @ApiModelProperty(value = "手机号")
    private String phone;
}
