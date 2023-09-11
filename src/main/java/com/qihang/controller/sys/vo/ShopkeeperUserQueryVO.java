package com.qihang.controller.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-13 12:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopkeeperUserQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "用户id")
    private Integer id;
    
    @ApiModelProperty(value = "用户名")
    private String nickname;
    
    @ApiModelProperty(value = "昵称")
    private String name;
    
    @ApiModelProperty(value = "头像")
    private String avatar;
    
    @ApiModelProperty(value = "状态0：正常，1：锁定")
    private String status;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    
    @ApiModelProperty(value = "店铺id")
    private Integer tenantId;
    
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    
    @ApiModelProperty(value = "店铺余额")
    private BigDecimal shopBalance;
    
    @ApiModelProperty(value = "店铺logo")
    private String shopLogo;
}
