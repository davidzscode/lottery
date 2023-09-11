package com.qihang.controller.order.admin.lottery.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-28 1:28
 */
@Data
public class TicketingDTO {

    private Integer id;

    @ApiModelProperty(value = "订单状态 0 待出票 1 待开奖 2 未中奖 3 待派奖 4 已派奖 5 拒绝")
    private String state;
}
