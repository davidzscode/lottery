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
public class SetSalespersonDTO implements Serializable {
    
    @NotNull
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    
    
}
