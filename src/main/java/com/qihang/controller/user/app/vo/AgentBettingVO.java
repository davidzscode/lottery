package com.qihang.controller.user.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lihv
 * @since 2023/7/29
 */
@Data
public class AgentBettingVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "订单表id")
    private Integer id;
    
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    
    @ApiModelProperty(value = "手机号")
    private String phone;
    
    @ApiModelProperty(value = "昵称")
    private String nickname;
    
    @ApiModelProperty(value = "投注金额")
    private BigDecimal price = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
}
