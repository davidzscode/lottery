package com.qihang.controller.user.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-06 23:09
 */
@Data
public class RealDTO implements Serializable {
    @ApiModelProperty(value = "姓名", required = true)
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "身份证号码", required = true)
    @NotBlank(message = "身份证号码不能为空")
    private String idCard;
}
