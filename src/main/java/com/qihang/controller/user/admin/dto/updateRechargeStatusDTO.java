package com.qihang.controller.user.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class updateRechargeStatusDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否是充值用户")
    private String rechargeStatus;
}
