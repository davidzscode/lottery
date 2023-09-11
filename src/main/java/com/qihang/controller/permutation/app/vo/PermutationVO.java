package com.qihang.controller.permutation.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: qihang
 * @description:
 * @time: 2022-08-01 17:50
 */
@Data
public class PermutationVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "期号")
    private Integer stageNumber;

    @ApiModelProperty(value = "组合")
    private String content;

    @ApiModelProperty(value = "投注方式")
    private String mode;

    @ApiModelProperty(value = "预测奖金（保留小数点后2位）")
    private BigDecimal forecastBonus;
}
