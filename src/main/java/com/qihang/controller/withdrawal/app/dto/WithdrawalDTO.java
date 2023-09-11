package com.qihang.controller.withdrawal.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-06 23:09
 */
@Data
public class WithdrawalDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "提现金额", required = true)
    @NotNull(message = "提现金额不能为空")
    private BigDecimal amount;

    @ApiModelProperty(value = "0 支付宝 1 银行卡", required = true)
    @NotBlank(message = "提现方式不能为空")
    private String type;
}
