package com.qihang.controller.order.app.pay.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author qihang
 * @since 2022-10-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayCreateOrderDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "金额", required = true)
    @NotNull(message = "金额不能为空")
    private BigDecimal price;
    
    @ApiModelProperty(value = "订单类型", required = true)
    @NotBlank(message = "订单类型不能为空")
    private String type;
    
    @ApiModelProperty(value = "支付类型", required = true)
    @NotBlank(message = "支付类型不能为空")
    private String payType;
    
    @ApiModelProperty(value = "支付通道", required = true)
    @NotNull(message = "支付通道不能为空")
    private Integer channelId;
}
