package com.qihang.controller.user.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-14 17:37
 */
@Data
public class UserRefreshDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "刷新token不能为空")
    @ApiModelProperty(value = "刷新token", required = true)
    private String token;
}
