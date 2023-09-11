package com.qihang.controller.dict.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author qihang
 * @since 2022-11-14
 */
@Data
public class SysDictDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "appid")
    @NotBlank(message = "appid不能为空")
    private String aliPayAppId;

    @ApiModelProperty(value = "支付公钥")
    @NotBlank(message = "支付公钥不能为空")
    private String alipayPublicKey;

    @ApiModelProperty(value = "支付私钥")
    @NotBlank(message = "支付私钥不能为空")
    private String alipayPrivateKey;

    @ApiModelProperty(value = "租户id", required = true)
    @NotNull(message = "租户id不能为空")
    private Integer tenantId;

}
