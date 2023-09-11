package com.qihang.controller.order.app.lottery.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-22 10:08
 */
@Data
public class DeleteOrderVO implements Serializable {
    
    @ApiModelProperty(value = "订单id", required = true)
    @NotBlank(message = "订单id不能为空")
    private String orderId;
    
}
