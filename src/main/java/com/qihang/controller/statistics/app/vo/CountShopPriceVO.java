package com.qihang.controller.statistics.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lihv
 * @since 2023/7/29
 */
@Data
public class CountShopPriceVO extends BaseVO {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "出票总金额")
    private BigDecimal totalOutTicketPrice;
    @ApiModelProperty(value = "中奖总金额")
    private BigDecimal totalWinningPrice;
    @ApiModelProperty(value = "充值总金额")
    private BigDecimal totalRechargePrice;
    @ApiModelProperty(value = "提现总金额")
    private BigDecimal totalWithdrawalPrice;
    @ApiModelProperty(value = "加款总金额")
    private BigDecimal totalAddFundsPrice;
    @ApiModelProperty(value = "扣款总金额")
    private BigDecimal totalDeductionPrice;
    
}
