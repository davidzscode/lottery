package com.qihang.controller.user.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-10 22:24
 */
@Data
public class AgentVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "彩金余额")
    private BigDecimal gold = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "奖金余额")
    private BigDecimal price = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "今日投注")
    private BigDecimal todayBetting = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总投注")
    private BigDecimal totalBetting = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "今日充值")
    private BigDecimal todayRecharge = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总充值")
    private BigDecimal totalRecharge = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "今日提现")
    private BigDecimal todayWithdrawal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总提现")
    private BigDecimal totalWithdrawal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "今日派奖")
    private BigDecimal todayAward = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总派奖")
    private BigDecimal totalAward = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    @ApiModelProperty(value = "客户数量")
    private Integer countPersons;
    
    @ApiModelProperty(value = "今日出票")
    private BigDecimal todayOutTicket = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总出票")
    private BigDecimal totalOutTicket = BigDecimal.ZERO;
}
