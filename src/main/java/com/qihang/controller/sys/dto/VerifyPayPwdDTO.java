package com.qihang.controller.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-06-28 10:16
 */
@Data
public class VerifyPayPwdDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "支付密码", required = true)
    @NotNull(message = "支付密码不能为空")
    private Integer payPwd;
}
