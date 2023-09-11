package com.qihang.controller.shop.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-13 11:31
 */
@Data
public class AdminShopVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "id")
    private Integer id;
    
    @ApiModelProperty(value = "名称")
    private String name;
    
    @ApiModelProperty(value = "logo")
    private String logo;
    
    @ApiModelProperty(value = "后台系统用户名")
    private String username;
    
    @ApiModelProperty(value = "店铺余额")
    private BigDecimal balance;
    
    @ApiModelProperty(value = "上下架")
    private String line;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    @ApiModelProperty(value = "店主id")
    private Integer shopkeeperId;
    
    @ApiModelProperty(value = "店主昵称")
    private String shopkeeperNickname;
}
