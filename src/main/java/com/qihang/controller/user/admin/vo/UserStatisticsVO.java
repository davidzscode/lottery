package com.qihang.controller.user.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-06 22:48
 */
@Data
public class UserStatisticsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "父级")
    private String parentName;

    @ApiModelProperty(value = "奖金")
    private BigDecimal price;

    @ApiModelProperty(value = "彩金")
    private BigDecimal gold;

    @ApiModelProperty(value = "是否是代理 0 否 1 是")
    private String isAgent;

    @ApiModelProperty(value = "提现金额")
    private BigDecimal withdrawalPrice;

    @ApiModelProperty(value = "充值金额")
    private BigDecimal rechargePrice;

    @ApiModelProperty(value = "下注金额")
    private BigDecimal bettingPrice;
}
