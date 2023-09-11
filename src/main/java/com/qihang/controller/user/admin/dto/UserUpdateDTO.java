package com.qihang.controller.user.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-12 15:38
 */
@Data
public class UserUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "是否是代理 0 否 1 是")
    private String isAgent;
}
