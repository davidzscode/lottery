package com.qihang.controller.statistics.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-06 13:48
 */
@Data
public class StatisticsVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "待出票数量")
    private Long ticketingCount;

    @ApiModelProperty(value = "待派奖数量")
    private Long awardCount;

    @ApiModelProperty(value = "待提现数量")
    private Long withdrawalCount;

    @ApiModelProperty(value = "彩民数量")
    private Long userCount;

    @ApiModelProperty(value = "当天注册数量")
    private Long registerCount;

    @ApiModelProperty(value = "当天实名数量")
    private Long realCount;

    @ApiModelProperty(value = "账户总额")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "今日总充值")
    private BigDecimal rechargePrice;

    @ApiModelProperty(value = "总充值")
    private BigDecimal totalRechargePrice;

    @ApiModelProperty(value = "今日提现")
    private BigDecimal withdrawalPrice;

    @ApiModelProperty(value = "待派奖金")
    private BigDecimal awardPrice;

    @ApiModelProperty(value = "今日投注")
    private BigDecimal bettingPrice;

    @ApiModelProperty(value = "已派奖金额")
    private BigDecimal alreadyAwardPrice;

    @ApiModelProperty(value = "昨日投注金额")
    private BigDecimal yesterdayBettingPrice;

    @ApiModelProperty(value = "昨日派奖金额")
    private BigDecimal yesterdayAwardPrice;

    @ApiModelProperty(value = "昨日盈利金额")
    private BigDecimal yesterdayProfitPrice;

    @ApiModelProperty(value = "总盈利金额")
    private BigDecimal totalProfitPrice;

    @ApiModelProperty(value = "总投注")
    private BigDecimal totalBetting;
}
