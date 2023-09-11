package com.qihang.controller.user.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class updateUserStarStatusDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否星级用户")
    private String isStar;
}
