package com.qihang.controller.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lihv
 * @since 2023/8/6
 */
@Data
public class EditShopLineDTO {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "id")
    @NotNull(message = "不能为空")
    private Integer id;
    
    @ApiModelProperty(value = "0/1  上架/下架")
    @NotNull(message = "不能为空")
    private String line;
}
