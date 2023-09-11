package com.qihang.controller.shop.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-13 11:31
 */
@Data
public class ShopDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号")
    private String phone;

}
