package com.qihang.controller.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author lihv
 * @since 2023/8/6
 */
@Data
public class EditShopRateDTO {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "id")
    @NotNull(message = "店铺id不能为空")
    private Integer id;
    
    @ApiModelProperty(value = "扣款比率")
    @NotNull(message = "扣款比率不能为空")
    private BigDecimal shopRate;
}
