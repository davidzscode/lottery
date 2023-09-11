package com.qihang.controller.order.admin.pay.dto;

import com.qihang.common.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-12 15:38
 */
@Data
public class PayOrderQueryDTO extends PageDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "0 待支付 1 已支付")
    private String state;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "订单类型 0 充值 1购买足球 2 购买篮球 3.购买北单 4排列三 5.提现 6.竞彩足球派奖 7.竞彩篮球派奖 8. 北京单场派奖 9.排列3派奖 10 竞彩足球退票 11 竞彩篮球退票 12 北京单场退票 13 排列3退票")
    private String type;
}
