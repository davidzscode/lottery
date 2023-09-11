package com.qihang.controller.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-06-28 10:16
 */
@Data
public class UpdateUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "原密码")
    private String oldPassword;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "原支付密码")
    private Integer oldPayPwd;

    @ApiModelProperty(value = "支付密码")
    private Integer payPwd;

    @ApiModelProperty(value = "用户名")
    private String username;
}
