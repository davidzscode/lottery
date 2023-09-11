package com.qihang.controller.shop.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-13 11:31
 */
@Data
public class ShopVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "logo")
    private String logo;

    @ApiModelProperty(value = "后台系统用户名")
    private String username;

}
