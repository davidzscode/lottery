package com.qihang.controller.user.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lihv
 * @since 2023/8/6
 */
@Data
public class GetAllSaleVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    
    @ApiModelProperty(value = "用户id")
    private Integer id;
    
    @ApiModelProperty(value = "手机号")
    private String phone;
    
    @ApiModelProperty(value = "昵称")
    private String nickname;
    
    @ApiModelProperty(value = "店铺id")
    private Integer tenantId;
    
    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "销售头像")
    private String avatar;

    @ApiModelProperty(value = "模拟用户")
    private Integer moni;

    @ApiModelProperty(value = "彩金")
    private BigDecimal gold = BigDecimal.ZERO;

    @ApiModelProperty(value = "奖金")
    private BigDecimal price = BigDecimal.ZERO;

    @ApiModelProperty(value = "余额")
    private BigDecimal balance = BigDecimal.ZERO;
}
