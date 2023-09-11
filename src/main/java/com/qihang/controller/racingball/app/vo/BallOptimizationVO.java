package com.qihang.controller.racingball.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author qihang
 * @since 2022-10-05
 */
@Data
@Accessors(chain = true)
public class BallOptimizationVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "过关类型")
    private String type;

    @ApiModelProperty(value = "注数分布")
    private Integer notes;

    @ApiModelProperty(value = "预测奖金（保留小数点后2位）")
    private BigDecimal forecastBonus;

    @ApiModelProperty(value = "是否展开")
    private Boolean isShow;

    @ApiModelProperty(value = "下注的比赛组合")
    private List<BallCombinationVO> ballCombinationList;
}
