package com.qihang.controller.shop.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-13 11:31
 */
@Data
public class AddShopDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "店铺名")
    @NotBlank(message = "店铺名不能为空")
    private String name;

    @ApiModelProperty(value = "logo")
    @NotBlank(message = "logo不能为空")
    private String logo;

    @ApiModelProperty(value = "余额")
    @NotNull(message = "余额不能为空")
    private BigDecimal balance;

    @ApiModelProperty(value = "系统用户名")
    @NotBlank(message = "系统用户名不能为空")
    private String sysUsername;

    @ApiModelProperty(value = "系统用户密码")
    @NotBlank(message = "系统用户密码不能为空")
    private String sysPassword;

    @ApiModelProperty(value = "支付密码")
    @NotNull(message = "支付密码不能为空")
    private Integer payPwd;

    @ApiModelProperty(value = "通知邮箱")
    private String email;

    @ApiModelProperty(value = "app手机号")
    @NotBlank(message = "app手机号不能为空")
    private String appPhone;

    @ApiModelProperty(value = "app用户密码")
    @NotBlank(message = "app用户密码不能为空")
    private String appPassword;
}
