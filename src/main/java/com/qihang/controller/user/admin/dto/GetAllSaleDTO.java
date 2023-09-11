package com.qihang.controller.user.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lihv
 * @since 2023/8/6
 */
@Data
public class GetAllSaleDTO {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "昵称")
    private String nickname;
    
    @ApiModelProperty(value = "用户id")
    private Integer userId;
}
