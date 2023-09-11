package com.qihang.controller.user.app.vo;

import com.qihang.common.vo.CommonListVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lihv
 * @since 2023/7/27
 */
@Data
public class AgentSumVO extends CommonListVO<AgentVO> {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "总投注")
    private BigDecimal totalBetting = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总充值")
    private BigDecimal totalRecharge = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总提现")
    private BigDecimal totalWithdrawal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总派奖")
    private BigDecimal totalAward = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总出票")
    private BigDecimal totalOutTicket = BigDecimal.ZERO;
}
