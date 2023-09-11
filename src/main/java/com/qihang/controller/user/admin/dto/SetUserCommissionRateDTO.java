package com.qihang.controller.user.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lihv
 * @since 2023/8/6
 */
@Data
public class SetUserCommissionRateDTO implements Serializable {
    
    @NotNull
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    
    @NotNull
    @ApiModelProperty(value = "用户发单的佣金比例(n%)")
    private Integer commissionRate;
}
