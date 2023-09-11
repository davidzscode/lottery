package com.qihang.controller.user.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-08-01 17:50
 */
@Data
public class BindingDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "银行账户")
    private String bankNumber;

    @ApiModelProperty(value = "支付宝账户")
    private String zfbNumber;

    @ApiModelProperty(value = "银行名称")
    private String bankName;
}
