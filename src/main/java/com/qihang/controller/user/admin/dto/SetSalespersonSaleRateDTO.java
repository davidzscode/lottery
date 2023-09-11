package com.qihang.controller.user.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lihv
 * @since 2023/8/6
 */
@Data
public class SetSalespersonSaleRateDTO implements Serializable {
    
    @NotNull
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    
    @NotNull
    @ApiModelProperty(value = "销售的销售佣金比例(小数)")
    private BigDecimal saleRate;
}
