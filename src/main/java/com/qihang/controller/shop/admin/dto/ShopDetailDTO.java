package com.qihang.controller.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lihv
 * @since 2023/8/7
 */
@Data
public class ShopDetailDTO {
    private static final long serialVersionUID = 1L;
    
    @NotNull
    @ApiModelProperty(value = "店铺id")
    private Integer id;
}
