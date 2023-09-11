package com.qihang.controller.user.admin.vo;

import com.qihang.common.vo.CommonListVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-07 21:51
 */
@Data
public class AdminStatisticsVO extends CommonListVO<UserStatisticsVO> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "提现总金额")
    private BigDecimal totalWithdrawalPrice;

    @ApiModelProperty(value = "总奖金")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "总彩金")
    private BigDecimal totalGold;

    @ApiModelProperty(value = "充值总金额")
    private BigDecimal totalRechargePrice;

    @ApiModelProperty(value = "下注总金额")
    private BigDecimal totalBettingPrice;
}
