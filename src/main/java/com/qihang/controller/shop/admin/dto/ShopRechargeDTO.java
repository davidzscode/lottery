package com.qihang.controller.shop.admin.dto;

import com.qihang.common.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-13 11:31
 */
@Data
public class ShopRechargeDTO extends PageDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @NotNull(message = "店铺id不能为空")
    private Integer id;

    @ApiModelProperty(value = "余额")
    @NotNull(message = "余额不能为空")
    private BigDecimal balance;
}
